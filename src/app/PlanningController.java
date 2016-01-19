/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import model.BallBoy;
import model.BallBoyCollection;
import model.CourtCollection;
import model.Match;
import model.MatchCollection;
import model.Person;
import model.Player;
import model.PlayerCollection;
import model.Reservation;
import model.ReservationCollection;
import model.Umpire;
import model.UmpireCollection;
import view.planning.AddMatch;
import view.planning.Court;
import view.planning.CourtsContainer;
import view.planning.DayPane;
import view.planning.EditMatch;
import view.planning.Planning;

/**
 *
 * @author laurent
 */
public class PlanningController {
    
    public static final String[] PHASES_NAME = new String[] { "Qualification", "Huitième de finale", "Quart de finale", "Demie finale", "Finale" };
   
    /**
     * Fill the planning with Matchs and Reservations
     */
    public static void refreshPlanning(){
        
        JOptionPane loadingPane = new JOptionPane("Chargement en cours...", JOptionPane.INFORMATION_MESSAGE);
        
        JDialog loadingDialog = loadingPane.createDialog("Chargement..."); 

        JProgressBar progress = new JProgressBar(0, 100);
        progress.setIndeterminate(true); 

        loadingDialog.add(BorderLayout.NORTH, progress);
        loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
        loadingDialog.setSize(300, 90);
        
        // Affichage de la boîte de dialogue en arrière plan
        Thread t = new Thread(() -> {
            loadingDialog.setVisible(true);
        });
        t.start();
        
        ArrayList<DayPane> dayPanes = Planning.getDayPanes();
                
        DayPane currentDay;
        CourtsContainer currentSlot;
        Court currentCourt;
        
        ArrayList<Reservation> reservationsOfTheDay;
        ArrayList<Match> matchsOfTheDay;
        
        int dayNumber;
        int interfaceCourtId;
                
        for(dayNumber = 0; dayNumber < Settings.NB_DAYS; dayNumber++) {
            
            currentDay = dayPanes.get(dayNumber);

            reservationsOfTheDay = ReservationCollection.read(Settings.generateDate(dayNumber), Settings.generateDate(dayNumber+1));
            
            for(CourtsContainer courtsContainer : currentDay.getCourtsContainer()){
                for(Court court : courtsContainer.getCourts()){
                    court.initComponents(); // Reset Court with the default view
                    court.setMatch(null);
                }
            }
            
            for(Reservation currentReservation : reservationsOfTheDay) {
                
                currentSlot = currentDay.getCourtsContainer(currentReservation.getSlotId());
                
                interfaceCourtId = Court.getInterfaceCourt(currentReservation.getCourtId());
                
                currentCourt = currentSlot.getCourt(interfaceCourtId);
                currentCourt.setMatch(currentReservation);
                
            }
            
            matchsOfTheDay = MatchCollection.readByDate(Settings.generateDate(dayNumber));

            for(Match currentMatch : matchsOfTheDay){
                
                currentSlot = currentDay.getCourtsContainer(currentMatch.getSlot());
                interfaceCourtId = Court.getInterfaceCourt(currentMatch.getCourt().getCourtId());
                
                currentCourt = currentSlot.getCourt(interfaceCourtId);
                currentCourt.setMatch(currentMatch);
                
            }
            
        }
                
        loadingPane.setVisible(false);
        loadingDialog.setVisible(false);
        
        loadingDialog.dispose();
        
        
    }
    
    /**
     * Init the planning with real court names
     */
    public static void initPlanning(){
        
        List<model.Court> courts = CourtCollection.readAll();
                
        for(DayPane dayPane : Planning.getDayPanes()){
            
            for(CourtsContainer courtsContainer : dayPane.getCourtsContainer()){
                
                for(Court court : courtsContainer.getCourts()){
                    
                    court.setTitle(courts.get(Court.getRealCourtId(court.getInterfaceCourtId())-1).getCourtName());
                    
                }
                
            }
            
        }
        
        
    }
        
    /**
     * Launch the EditMatch frame, fill datas and display it
     * @param match the match to edit
     */
    public static void initMatchEdition(Match match){
        
        EditMatch.getFrame(match);
        
        // List which contains the Person available for this match
        List<Person> personsPlayerA = new ArrayList<>();
        List<Person> personsPlayerB = new ArrayList<>();
        List<Person> personsBallBoy = new ArrayList<>();
        List<Person> personsUmpire = new ArrayList<>();
        List<Person> personsNetUmpire = new ArrayList<>();
        List<Person> personsWinner = new ArrayList<>();
        
        List<Player> matchPlayers = match.getPlayers();
        List<Umpire> matchUmpires = match.getUmpires();
        
        //===================== Player =====================\\
        
        // Add only valid Player
        for(Person p : PlayerCollection.getFreeAt(match.getDate(), match.getSlot())){// Récupérer Player compatibles){
            personsPlayerA.add((Player)p);
        }
                
        EditMatch.modelPlayerA.setPersons((ArrayList<Person>) personsPlayerA);
        
        // We select the first player (works both for double and simple matchs)
        EditMatch.modelPlayerA.setSelectedItem(matchPlayers.get(0));

        
        // Add only valid Players
        for(Person p : PlayerCollection.getFreeAt(match.getDate(), match.getSlot())){ //Récupérer Player compatibles
            personsPlayerB.add((Player)p);
        }
        EditMatch.modelPlayerB.setPersons((ArrayList<Person>) personsPlayerB);
        
        // We select the first player (works both for double and simple matchs)
        EditMatch.modelPlayerB.setSelectedItem(matchPlayers.get(1));
        
        //===================== BallBoy =====================\\
        
        // Add only valid BallBoys
        for(Person p : BallBoyCollection.getFreeAt(match.getDate(), match.getSlot())){ //Récupérer BallBoy compatibles){
            personsBallBoy.add((BallBoy)p);
        }
        EditMatch.modelBallBoys.setPersons((ArrayList<Person>) personsBallBoy);        
        
        EditMatch.setSelectedBallBoys(match.getBallboys()); // Select match's current BallBoys 
        
        //===================== Umpire =====================\\
        
        // We add the umpires to the lists
        for(Person p : UmpireCollection.getFreeAt(match.getDate(), match.getSlot())){ //Récupérer Umpire compatibles){
            if(((Umpire)p).getLevel().equals(Umpire.CHAIR_LEVEL)){
                personsUmpire.add((Umpire)p);
            }else{
                personsNetUmpire.add((Umpire)p);
            }
        }
        
        EditMatch.modelUmpire.setPersons((ArrayList<Person>) personsUmpire);
        EditMatch.modelUmpire.setSelectedItem(matchUmpires.get(0)); // We select the chair umpire of this match
               
        //===================== net Umpire =====================\\
        
        EditMatch.modelNetUmpires.setPersons((ArrayList<Person>) personsNetUmpire);
        // There's net umpires only if we have more than 1 umpire (the chair umpire)
        if(matchUmpires.size() > 1){
            EditMatch.setSelectedNetUmpires(matchUmpires.subList(1, matchUmpires.size()-1));
        }        
        //===================== Phase =====================\\
        
        String phaseName = PlanningController.getPhaseNameById(match.getPhase());
        EditMatch.modelPhase.setSelectedItem(phaseName);
        
        //===================== winner =====================\\
        
        // Add only valid winners
        for(Person p : match.getPlayers()){// Récupérer Player compatibles){
            personsWinner.add((Player)p);
        }
        
        EditMatch.modelWinner.setPersons((ArrayList<Person>) personsWinner);
        // Select the real winner
        if(((Player)match.getWinner()).getPersonId() > 0){
            EditMatch.modelWinner.setSelectedItem(match.getWinner());
        }
        //===================== result =====================\\
        
        EditMatch.inputResult.setText(match.getResult());
        
        EditMatch.display(match);
        
        
    }

    /**
     * Launch the AddMatch frame, fill datas and display it
     * @param matchType kind of match (Match.KIND_SIMPLE or Match.KIND_DOUBLE)
     * @param dayNumber dayId number of the match
     * @param slotId slotId of the match
     * @param courtId courtId of the match
     */
    public static void initMatchCreation(int matchType, int dayNumber, int slotId, int courtId){
        
        AddMatch.getFrame(matchType, dayNumber, slotId, courtId);
        Date date = Settings.generateDate(dayNumber);
        
        /*System.out.println("initMatchCreation " + matchType + " - " + dayNumber + " : " + slotId + " " + courtId);
        System.out.println("Date : " + date);
        System.out.println(PlayerCollection.getFreeAt(date, slotId));
        System.out.println(BallBoyCollection.getFreeAt(date, slotId));
        System.out.println(UmpireCollection.getFreeAt(date, slotId));*/
                
        List<Person> playersA = new ArrayList<>();

        // Add only valid Players
        for(Person p : PlayerCollection.getFreeAt(date, slotId)){ //Récupérer Player compatibles){
            if((matchType == Match.KIND_DOUBLE && ((Player)p).getPartner().getPersonId() > 0) || matchType == Match.KIND_SIMPLE){
                playersA.add((Player)p);
            }
        }
        AddMatch.modelPlayerA.setPersons((ArrayList<Person>) playersA);
        
        List<Person> playersB = new ArrayList<>();
        // Add only valid Players
        for(Person p : PlayerCollection.getFreeAt(date, slotId)){ //Récupérer Player compatibles){
            if((matchType == Match.KIND_DOUBLE && ((Player)p).getPartner().getPersonId() > 0) || matchType == Match.KIND_SIMPLE){
                playersB.add((Player)p);
            }
        }
        AddMatch.modelPlayerB.setPersons((ArrayList<Person>) playersB);
        
        List<Person> ballboys = new ArrayList<>();
        
        // Add only valid BallBoys
        for(Person p : BallBoyCollection.getFreeAt(date, slotId)){ //Récupérer BallBoy compatibles){
            ballboys.add((BallBoy)p);
        }
        AddMatch.modelBallBoys.setPersons((ArrayList<Person>) ballboys);
        
        List<Person> umpires = new ArrayList<>();
        List<Person> netUmpires = new ArrayList<>();

        for(Person p : UmpireCollection.getFreeAt(date, slotId)){ //Récupérer Umpire compatibles){
            if(((Umpire)p).getLevel().equals(Umpire.CHAIR_LEVEL) && UmpireCollection.getCountOfMatch(((Umpire)p), matchType) <= 1){
                umpires.add((Umpire)p);
            }else{
                netUmpires.add((Umpire)p);
            }
        }
        AddMatch.modelUmpire.setPersons((ArrayList<Person>) umpires);
        AddMatch.modelNetUmpires.setPersons((ArrayList<Person>) netUmpires);
        
        AddMatch.display(matchType, dayNumber, slotId, courtId);
                           
    }
    
    /**
     * Called by the "Valid" button of the AddMatch frame
     * Add a match with properties selected in AddMatch frame
     */
    public static void createMatch(){
        
        String round = (String) AddMatch.modelPhase.getSelectedItem();
        int phase = PlanningController.getPhaseIdByName(round);
        
        int kind = AddMatch.matchType;
        
        int courtId = Court.getRealCourtId(AddMatch.interfaceCourtId);
        int slotId = AddMatch.slotId;
        Date date = Settings.generateDate(AddMatch.dayNumber);
                
        Player playerA = (Player) AddMatch.modelPlayerA.getSelectedItem();
        Player playerB = (Player) AddMatch.modelPlayerB.getSelectedItem();
        
        List<Player> players = new ArrayList<>();
        players.add(playerA);
        players.add(playerB);
        
        if(kind == Match.KIND_DOUBLE){
            players.add(playerA.getPartner());
            players.add(playerB.getPartner());
        }
        
        List<Umpire> umpires = new ArrayList<>();
        
        Umpire umpire = (Umpire) AddMatch.modelUmpire.getSelectedItem();
        umpires.add(umpire); // Item 0 is the chair umpire
        
        int[] selectedNetUmpires = AddMatch.listNetUmpires.getSelectedIndices();

        for (int selectedNetUmpire : selectedNetUmpires) {
            umpires.add((Umpire) AddMatch.modelNetUmpires.getElementAt(selectedNetUmpire));
        }
        
        int[] selectedBallBoys = AddMatch.listBallBoys.getSelectedIndices();
        List<BallBoy> ballboys = new ArrayList<>();

        for (int selectedBallBoy : selectedBallBoys) {
            ballboys.add((BallBoy) AddMatch.modelBallBoys.getElementAt(selectedBallBoy));
        }        
                        
        Match match = new Match();
        match.setBallboys((ArrayList<BallBoy>) ballboys);
        match.setCourt(CourtCollection.readOne(courtId));
        match.setDate(date);
        match.setKind(AddMatch.matchType);
        match.setPhase(phase);
        match.setPlayers((ArrayList<Player>) players);
        match.setSlot(slotId);
        match.setUmpires((ArrayList<Umpire>) umpires);
        
        List<String> errors = new ArrayList<>();
        
        if(ballboys.isEmpty()){ errors.add("Vous devez sélectionner X ramasseurs de balles"); } // If there's no ballboys selected
        if(umpires.size() < 2){ errors.add("Un match ne peut pas se jouer sans arbitres !"); } // If there's no umpires
        
        if(!MatchCollection.checkCountOfMatchOfUmpire(match, kind)){
            errors.add("L'arbitre de chaise a déjà arbitré 2 matchs durant ce tournoi.");
        }
        
        if(!MatchCollection.checkPhasesOrder(match)){
            errors.add("Le tour de " + round + " est déjà terminé !");
        }
        
        if(!MatchCollection.checkPlayersAndUmpireNationality(match)){
            errors.add("L'arbitre ne doit pas avoir la même nationalité que celles des joueurs");
        }
        
        if(!MatchCollection.checkPlayersDisponibility(match)){
            errors.add("Les joueurs sélectionnés ne sont pas disponible pour jouer ce match");
        }
        
        // Constraints checked and correct
        if(errors.isEmpty()){
            MatchCollection.create(match);
            PlanningController.refreshPlanning();
            AddMatch.close();
            JOptionPane.showMessageDialog (null, "Match enregistré avec succès !", "Enregistrement", JOptionPane.INFORMATION_MESSAGE);
        }else{
            System.err.println(errors);
            JOptionPane.showMessageDialog (null, errors.toString(), "Erreur : contraintes non respectées", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    /**
     * Called by the "Valid" button of the EditMatch frame
     * Edit a match with properties selected in EditMatch frame
     */
    public static void editMatch(){
        
        String kind = (String) EditMatch.modelPhase.getSelectedItem();
        String kindLowerCase = kind.toLowerCase();
        
        Match match = EditMatch.match;
        
        int phase = Match.PHASE_QUALIFICAITON;
        if(kindLowerCase.startsWith("huitième"))
            phase = Match.PHASE_8EME;
        else if(kindLowerCase.startsWith("quart"))
            phase = Match.PHASE_QUART;
        else if(kindLowerCase.startsWith("demie"))
            phase = Match.PHASE_SEMIFINAL;
        else if(kindLowerCase.startsWith("finale"))
            phase = Match.PHASE_FINAL;
        
        //int courtId = match.getCourt().getCourtId();
        //int slotId = match.getSlot();
        //Date date = match.getDate();
        
        Player playerA = (Player) EditMatch.modelPlayerA.getSelectedItem();
        Player playerB = (Player) EditMatch.modelPlayerB.getSelectedItem();
        
        List<Player> players = new ArrayList<>();
        players.add(playerA);
        players.add(playerB);
        
        List<Umpire> umpires = new ArrayList<>();
        
        Umpire umpire = (Umpire) EditMatch.modelUmpire.getSelectedItem();
        umpires.add(umpire); // Item 0 is the chair umpire
        
        List<Umpire> netUmpires = EditMatch.getNetUmpires();
        umpires.addAll(netUmpires);
        
        
        List<BallBoy> ballboys = EditMatch.getBallBoys();
        
        Player  winner = (Player) EditMatch.modelWinner.getSelectedItem();
        String result = EditMatch.inputResult.getText();
        
        match.setPlayers((ArrayList<Player>) players);
        match.setPhase(phase);
        match.setResult(result);
        match.setUmpires((ArrayList<Umpire>) umpires);
        match.setWinner(winner);
        
        List<String> errors = new ArrayList<>();
        
        if(!MatchCollection.checkCountOfMatchOfUmpire(match, match.getKind())){
            errors.add("L'arbitre de chaise a déjà arbitré 2 matchs durant ce tournoi.");
        }
        
        if(!MatchCollection.checkPhasesOrder(match)){
            errors.add("Le tour de " + phase + " est déjà terminé !");
        }
        
        if(!MatchCollection.checkPlayersAndUmpireNationality(match)){
            errors.add("L'arbitre ne doit pas avoir la même nationalité que celles des joueurs");
        }
        
        if(!MatchCollection.checkPlayersDisponibility(match)){
            errors.add("Les joueurs sélectionnés ne sont pas disponible pour jouer ce match");
        }
        
        // Constraints checked and correct
        if(errors.isEmpty()){
            MatchCollection.update(match);
            System.out.println(match);
            PlanningController.refreshPlanning();
            EditMatch.close();
            JOptionPane.showMessageDialog (null, "Match modifié avec succès !", "Edition", JOptionPane.INFORMATION_MESSAGE);
        }else{
            System.err.println(errors);
            JOptionPane.showMessageDialog (null, errors.toString(), "Erreur : contraintes non respectées", JOptionPane.ERROR_MESSAGE);
        }        
        
    }
    
    public static void deleteMatch(Match match){
        
        MatchCollection.delete(match);
        JOptionPane.showMessageDialog (null, "Match supprimé avec succès ! " + match.getMatchId(), "Suppression", JOptionPane.INFORMATION_MESSAGE);
        PlanningController.refreshPlanning();
    }
    
    /**
     * Get the phase id by its name
     * @param phaseName
     * @return phaseId
     */
    public static int getPhaseIdByName(String phaseName){
        
        phaseName = phaseName.toLowerCase();
        int phase = Match.PHASE_QUALIFICAITON;
        if(phaseName.startsWith("huitième"))
            phase = Match.PHASE_8EME;
        else if(phaseName.startsWith("quart"))
            phase = Match.PHASE_QUART;
        else if(phaseName.startsWith("demie"))
            phase = Match.PHASE_SEMIFINAL;
        else if(phaseName.startsWith("finale"))
            phase = Match.PHASE_FINAL;
        
        return phase;
    }

    /**
     * Get the phase name by its id
     * @param phaseId
     * @return phaseName
     */
    public static String getPhaseNameById(int phaseId){
        
        String phaseName = PlanningController.PHASES_NAME[0];
        if(phaseId == Match.PHASE_8EME)
            phaseName = PlanningController.PHASES_NAME[1];
        else if(phaseId == Match.PHASE_QUART)
            phaseName = PlanningController.PHASES_NAME[2];
        else if(phaseId == Match.PHASE_SEMIFINAL)
            phaseName = PlanningController.PHASES_NAME[3];
        else if(phaseId == Match.PHASE_FINAL)
            phaseName = PlanningController.PHASES_NAME[4];
        
        return phaseName;
        
    }
         
}
