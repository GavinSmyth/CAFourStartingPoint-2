import java.util.*;

public class AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){

    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  public Move nextBestMove(Stack whiteP, Stack blackP){
    Move whiteMove, bestMove, attack;
    Stack white = (Stack) whiteP.clone();
    Stack black = (Stack) blackP.clone();
    int Score;
    int chosenPiece = 0;
    attack = null;
    Square blackPosition;
    while (!whiteP.empty()) {
      whiteMove = (Move) whiteP.pop();
      bestMove = whiteMove;
      //getting the centre of the board and assigning it to be one so the first move isnt random.
      //scoring the board to be one gives the ai a value to attack
      //the centre of the board is more valuable in chess then the outside.
      if ((bestMove.getStart().getYC() < bestMove.getLanding().getYC()) &&  (bestMove.getLanding().getXC() == 3) && (bestMove.getLanding().getYC() == 3) || (bestMove.getLanding().getXC() == 4) && (bestMove.getLanding().getYC() == 3)
              || (bestMove.getLanding().getXC() == 2) && (bestMove.getLanding().getYC() == 3)
              || (bestMove.getLanding().getXC() == 5) && (bestMove.getLanding().getYC() == 3)
              || (bestMove.getLanding().getXC() == 2) && (bestMove.getLanding().getYC() == 4)
              || (bestMove.getLanding().getXC() == 5) && (bestMove.getLanding().getYC() == 4)
              || (bestMove.getLanding().getXC() == 3) && (bestMove.getLanding().getYC() == 4)
              || (bestMove.getLanding().getXC() == 4) && (bestMove.getLanding().getYC() == 4)) {
        Score = 1;
        //assign best move
        // this allows the first move to always go to the centre of the board.
        if (Score > chosenPiece) {
          chosenPiece = Score;
          attack = bestMove;
        }
      }
      //this is just adding the remaining scores to the pieces.
      //AI will move to the centre of the board if it cant take a piece but if it can it will take the piece with the highest score
      //if the centre of the board is taken and it cant take a piece the ai agent will make a random move.
      while (!black.isEmpty()) {
        Score = 0;
        blackPosition = (Square) black.pop();
        if ((bestMove.getLanding().getXC() == blackPosition.getXC()) && (bestMove.getLanding().getYC() == blackPosition.getYC())) {

          if (blackPosition.getName().equals("BlackPawn")) {
            Score = 2;
          }
          else if (blackPosition.getName().equals("BlackKnight") || blackPosition.getName().equals("BlackBishop")) {
            Score = 3;
          }
          else if (blackPosition.getName().equals("BlackRook")) {
            Score = 5;
          }
          else if (blackPosition.getName().equals("BlackQueen")) {
            Score = 9;
          }
          else if (blackPosition.getName().equals("BlackKing")) {
            Score = 10;
          }
        }
        //Reassigning best score after weighing options
        if (Score > chosenPiece) {
          chosenPiece = Score;
          attack = bestMove;
        }
      }
      //This runs through the stack of possibilities. Without this, the AI would score the center of the board over pieces
      black = (Stack) blackP.clone();
    }
    // This will return the highest scoring move, if the score is not greater than 0 (ie it is 0), it will return a random move
    if (chosenPiece > 0) {
      System.out.println("Best move selected by AI agent:" + chosenPiece);
      return attack;
    }
    return randomMove(white);

//    Move selectedMove = new Move();
//    return selectedMove;
  }

  public Move twoLevelsDeep(Stack possibilities){
    Move selectedMove = new Move();
    return selectedMove;
  }
}
