package main;

import java.util.Scanner;

import ship.Direction;
import ship.Ship;

public class Partie {
	private static Scanner sc = new Scanner(System.in);
	private Player player1;
	private Player player2;
	private Player winner;
	private int turnNumber;
	
	public Partie() {
		super();
		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
		turnNumber = 0;
		
		this.displayPresentation();
		this.setPlayersShips();
		this.go();
	}
	
	public void setPlayersShips() {
		System.out.println("Now you will place your ships on your own Primary grid.");
		System.out.println("Let's start with "+player1.getName()+" : \n");
		player1.setShips();
		
		System.out.println(player2.getName()+" it's your turn : \n");
		player2.setShips();
	}
	
	public void displayPresentation() {
		System.out.println("----------- Welcome to the battleship game -----------\n");
		System.out.println("Two players will fight with their boats.");
		System.out.println("The goal is to destroy every other player ships.\n");
		
		System.out.println("To do that, you have two grids:");
		System.out.println("\t- The Primary grid which displays your own ships");
		System.out.println("\t- The Tracking grid which displays your attempts to destroye the other player ships\n");
		
		System.out.println("Here are the grid symbols significations: ");
		System.out.println("\t '-' : Empty box");
		System.out.println("\t 'B' : A part of one your ships (displayed only on the Primary grid) ");
		System.out.println("\t 'O' : A missed shoot");
		System.out.println("\t '*' : A ship part hit");
		System.out.println("\t 'X' : A destroyed ship\n");
		
		System.out.println("------------------------------------------------------\n");
		Partie.waitForEntry();
	}
	
	public void go() {
		Player currentPlayer;
		Player waitingPlayer;
		int col, row;
		boolean shot;
		
		while(player1.hasAnyShipLeft() && player2.hasAnyShipLeft()) {
			turnNumber++;
			if(turnNumber%2 == 1) {
				currentPlayer = player1;
				waitingPlayer = player2;
			}
			else {
				currentPlayer = player2;
				waitingPlayer = player1;
			}
			
			System.out.println("------- Turn "+turnNumber+ " --------");
			System.out.println("------- "+currentPlayer.getName()+ " -------\n");
			Partie.waitForEntry();
			
			currentPlayer.displayPrimaryGrid();
			waitingPlayer.displayTrackingGrid();
			
			System.out.println("Select a location to shoot on :");
			col = currentPlayer.selectColPosition();
			row = currentPlayer.selectRowPosition();
			shot = currentPlayer.shoot(waitingPlayer, col, row);
			
			if(shot)
				System.out.println("You shot an enemy ship !\n");
			Partie.waitForEntry();
			
			currentPlayer.displayPrimaryGrid();
			waitingPlayer.displayTrackingGrid();
			Partie.waitForEntry();
			Partie.clearConsole();
		}
		
		if(player1.hasAnyShipLeft())
			winner = player1;
		else
			winner = player2;
		
		System.out.println("************************ "+winner.getName()+" WON ************************ ");
		Partie.waitForEntry();
			
	}
	
	public static void clearConsole() {
		for(int i=0; i<100; i++)
			System.out.println();
	}
	
	public static void waitForEntry() {
		System.out.println("Press Enter to continue ...");
		sc.nextLine();
	}
}
