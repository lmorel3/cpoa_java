/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
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
        
    }
    
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
        
        List<Person> persons = new ArrayList<>();
        List<Player> matchPlayers = match.getPlayers();
        List<Umpire> matchUmpires = match.getUmpires();

        
        // Add only valid Umpires
        for(Person p : PlayerCollection.getFreeAt(match.getDate(), match.getSlot())){// Récupérer Player compatibles){
            persons.add((Player)p);
        }
        EditMatch.modelPlayerA.setPersons((ArrayList<Person>) persons);
        persons.clear();
        
        EditMatch.modelPlayerA.setSelectedItem(matchPlayers.get(0));

        
        // Add only valid Players
        for(Person p : PlayerCollection.getFreeAt(match.getDate(), match.getSlot())){ //Récupérer Player compatibles
            persons.add((Player)p);
        }
        EditMatch.modelPlayerB.setPersons((ArrayList<Person>) persons);
        persons.clear();
        
        EditMatch.modelPlayerB.setSelectedItem(matchPlayers.get(1));
        
        // Add only valid BallBoys
        for(Person p : BallBoyCollection.getFreeAt(match.getDate(), match.getSlot())){ //Récupérer BallBoy compatibles){
            persons.add((BallBoy)p);
        }
        EditMatch.modelBallBoys.setPersons((ArrayList<Person>) persons);
        persons.clear();
        
        
        EditMatch.setSelectedBallBoys(match.getBallboys()); // Select match's current BallBoys 
        
        for(Person p : UmpireCollection.getFreeAt(match.getDate(), match.getSlot())){ //Récupérer net Umpire compatibles){
            persons.add((Umpire)p);
        }
        EditMatch.modelUmpire.setPersons((ArrayList<Person>) persons);
        persons.clear();
        
        EditMatch.modelUmpire.setSelectedItem(matchUmpires.get(0));
        
        String phaseName = PlanningController.getPhaseNameById(match.getPhase());
        EditMatch.modelPhase.setSelectedItem(phaseName);
        
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
            playersA.add((Player)p);
        }
        AddMatch.modelPlayerA.setPersons((ArrayList<Person>) playersA);
        
        List<Person> playersB = new ArrayList<>();
        // Add only valid Players
        for(Person p : PlayerCollection.getFreeAt(date, slotId)){ //Récupérer Player compatibles){
            playersB.add((Player)p);
        }
        AddMatch.modelPlayerB.setPersons((ArrayList<Person>) playersB);
        
        List<Person> ballboys = new ArrayList<>();
        
        // Add only valid BallBoys
        for(Person p : BallBoyCollection.getFreeAt(date, slotId)){ //Récupérer BallBoy compatibles){
            ballboys.add((BallBoy)p);
        }
        AddMatch.modelBallBoys.setPersons((ArrayList<Person>) ballboys);
        
        List<Person> umpires = new ArrayList<>();

        for(Person p : UmpireCollection.getFreeAt(date, slotId)){ // Récupérer Umpire compatibles
            umpires.add((Umpire)p);
        }
        AddMatch.modelUmpire.setPersons((ArrayList<Person>) umpires);
        
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
        List<Umpire> netUmpires = new ArrayList<>();

        for (int selectedNetUmpire : selectedNetUmpires) {
            netUmpires.add((Umpire) AddMatch.modelNetUmpires.getElementAt(selectedNetUmpire));
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
        }else{
            JOptionPane.showMessageDialog (null, errors.toString(), "Erreur : contraintes non respectées", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    
    /**
     * Called by the "Valid" button of the EditMatch frame
     * Edit a match with properties selected in EditMatch frame
     */
    public static void editMatch(){
        
        String kind = (String) EditMatch.modelPhase.getSelectedItem();
        String kindLowerCase = kind.toLowerCase();
        
        int phase = Match.PHASE_QUALIFICAITON;
        if(kindLowerCase.startsWith("huitième"))
            phase = Match.PHASE_8EME;
        else if(kindLowerCase.startsWith("quart"))
            phase = Match.PHASE_QUART;
        else if(kindLowerCase.startsWith("demie"))
            phase = Match.PHASE_SEMIFINAL;
        else if(kindLowerCase.startsWith("finale"))
            phase = Match.PHASE_FINAL;
        
        int courtId = EditMatch.match.getCourt().getCourtId();
        int slotId = EditMatch.match.getSlot();
        Date date = EditMatch.match.getDate();
        
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
