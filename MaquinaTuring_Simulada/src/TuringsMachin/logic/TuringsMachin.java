/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuringsMachin.logic;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class TuringsMachin {

    private ArrayList belt;       
    private ArrayList headBelt;  
    private ArrayList<ArrayList> moves;  
    private ArrayList<ArrayList> movements;  
           
    public int indexHead;  
          
    private final String EMPTY = "#";
    private final String X = "X";   
    private final int NOT = 0;
    private final int LEFT = 1;
    private final int RIGHT = 2;
    
    public TuringsMachin() {
        this.movements = new ArrayList<>();
        this.moves  = new ArrayList<>();
        this.belt  = new ArrayList<>();
        this.headBelt  = new ArrayList<>();
        this.indexHead = 1;
    }
    
    
         
    public void addBelt(int number1, int number2, String logicOperator ){
        try{
         rebootBelt();
        
        belt.add(EMPTY);
        
        for(int x=0; x<number1; x++)
            belt.add("1");
        
        switch(logicOperator){
            
            case "Sumar":
                belt.add("+");
                break;
            
            case "Restar":
                belt.add("-");
                break;
            
            case "Multiplicar":
                belt.add("*");
                break;
            
            case "Dividir":
                belt.add("/");
                break;
        }
         
        for(int x=0; x<number2; x++)
            belt.add("1");
        
        switch(logicOperator){
            
            case "Sumar":
                for(int x=0; x<number1+number2; x++){
                    belt.add(EMPTY);
                }
                break;
            
            case "Restar":
                for(int x=0; x<Math.abs(number1-number2); x++){
                    belt.add(EMPTY);
                }
                break;
            
            case "Multiplicar":
                for(int x=0; x<number1*number2; x++){
                    belt.add(EMPTY);
                }
                break;
            
            case "Dividir":
                if(number2 == 0)
                    number2=1;
                
                for(int x=0; x<number1/number2; x++)
                    belt.add(EMPTY);
                
                belt.add(EMPTY);
                belt.add(EMPTY);
                belt.add(EMPTY);
                belt.add(EMPTY);
                belt.add(EMPTY);
                
                break;
        }
           
        
        belt.add(EMPTY);
        belt.add(EMPTY);
        belt.add(EMPTY);
        
        
    
        for(int x=0; x<belt.size(); x++){
            headBelt.add("_");
        }
        
        headBelt.set(indexHead, "\u2193");
        movements.add(new ArrayList(headBelt));
        moves.add(new ArrayList(belt));   
        }catch(Exception e){
            System.out.println(""+e.getMessage());
        }
        
    }
    
    
    public void rebootBelt(){
        indexHead=1;
        belt.clear();
        headBelt.clear();
        moves.clear();
        movements.clear();
    }
    
     
    public void rightTo(){
        if(indexHead<belt.size())
        indexHead++;
    }
     
    public void leftTo(){
        if(indexHead>0)
        indexHead--;
    }
    
    
    public boolean moveBeltHead(String read, String write, int move){ 
        
        
        headBelt.set(indexHead, "_");
        
        if(belt.get(indexHead)==read){

            belt.set(indexHead, write);

            if(move==1){
                leftTo();
            }else if(move==2){
                rightTo();
            }
            
            headBelt.set(indexHead, "\u2193");
            moves.add(new ArrayList(belt));
            movements.add(new ArrayList(headBelt));
            System.out.print(belt.get(indexHead)); 
            return true;
        }
        return false;
    }
   
    public void addEqual(){
        while(!belt.contains("=")){
            
            while(belt.get(indexHead) == "1" || belt.get(indexHead)=="+" || belt.get(indexHead)=="-" || belt.get(indexHead)=="*" || belt.get(indexHead)=="/"){
                    moveBeltHead("1", "1", RIGHT);
                    moveBeltHead("+","+",RIGHT);
                    moveBeltHead("-","-",RIGHT);
                    moveBeltHead("*","*",RIGHT);
                    moveBeltHead("/","/",RIGHT);
                    
            }
            
            if(!moveBeltHead(EMPTY,"=",LEFT))
                    break;
            
            while(belt.get(indexHead) == "1" || belt.get(indexHead)=="+" || belt.get(indexHead)=="-" || belt.get(indexHead)=="*" || belt.get(indexHead)=="/" || belt.get(indexHead)=="="){
                    moveBeltHead("1", "1", LEFT);
                    moveBeltHead("+","+",LEFT);
                    moveBeltHead("-","-",LEFT);
                    moveBeltHead("*","*",LEFT);
                    moveBeltHead("/","/",LEFT);
                    moveBeltHead("=","=",LEFT);
            }
            if(!moveBeltHead(EMPTY,EMPTY,RIGHT))
                    break;
            
        }
    }
    
    public int addition(int number1, int number2){ 
        
        addEqual();
        while(true){
           
            while(belt.get(indexHead) == X || belt.get(indexHead)=="+"){
                    moveBeltHead(X, X, RIGHT);
                    moveBeltHead("+","+",RIGHT);
            }

            if(!moveBeltHead("1",X,RIGHT))
                    break;

            while(belt.get(indexHead) == "1" || belt.get(indexHead) == "+" ){
                    moveBeltHead("1", "1", RIGHT);
                    moveBeltHead("+","+",RIGHT);
            }

            if(!moveBeltHead("=","=",RIGHT))
                break;

            while(belt.get(indexHead) == "1"){
                moveBeltHead("1", "1", RIGHT);         
            }

           if(!moveBeltHead(EMPTY,"1",LEFT))
                break;
           
           
            while(belt.get(indexHead) == "1" || belt.get(indexHead) == "+" || belt.get(indexHead) == "=" ){
                    moveBeltHead("1", "1", LEFT);
                    moveBeltHead("=","=",LEFT);
                    moveBeltHead("+","+",LEFT);
            }

            if(!moveBeltHead(X, X, RIGHT))
                break;
            
        }

        return number1 + number2;
    }
    
    
    public int subtraction(int number1, int number2){
        addEqual();
        while(true){


            if(!moveBeltHead("1", X, RIGHT))
                break;

            while(belt.get(indexHead) == "1" ){
                        moveBeltHead("1", "1", RIGHT);
            }

            if(!moveBeltHead("-", "-", RIGHT))
                break;

            while(belt.get(indexHead) == "Y" ){
                        moveBeltHead("Y", "Y", RIGHT);
            }

            if(!moveBeltHead("1", "Y", LEFT))
                break;

            while(belt.get(indexHead) == "1" || belt.get(indexHead) == "-" || belt.get(indexHead) == "Y" ){
                        moveBeltHead("1", "1", LEFT);
                        moveBeltHead("-","-",LEFT);
                        moveBeltHead("Y","Y",LEFT);
            }

            if(!moveBeltHead(X, X, RIGHT))
                break;

        }

        while(true){
            if(!moveBeltHead("=", "=", RIGHT))
                break;

            while(belt.get(indexHead) == "1"){
                moveBeltHead("1", "1", RIGHT);                  
            }

            if(!moveBeltHead(EMPTY, "1", LEFT))
                break;

            while(belt.get(indexHead) == "1" || belt.get(indexHead)=="Y" || belt.get(indexHead)=="-" || belt.get(indexHead)=="="){
                    moveBeltHead("1","1",LEFT);
                    moveBeltHead("Y","Y",LEFT);
                    moveBeltHead("-","-",LEFT);
                    moveBeltHead("=","=",LEFT);          
            }

            if(!moveBeltHead(X, X, RIGHT))
                break;

            if(!moveBeltHead("1", X, RIGHT))
                break;

            while(belt.get(indexHead) == "1" ){
                        moveBeltHead("1", "1", RIGHT);
            }

            if(!moveBeltHead("-", "-", RIGHT))
                break;

            while(belt.get(indexHead) == "Y" ){
                        moveBeltHead("Y", "Y", RIGHT);
            }
        }

        if(!moveBeltHead("-", "-", RIGHT))
                return number1-number2;

        while(belt.get(indexHead) == "Y" ){
                        moveBeltHead("Y", "Y", RIGHT);
        }

        if(!moveBeltHead("1", "Y", RIGHT))
                return number1-number2;
        
        while(belt.get(indexHead) == "1" || belt.get(indexHead) == "=" ){
                        moveBeltHead("1","1",RIGHT);
                        moveBeltHead("=","=",RIGHT);
        }
        
        if(!moveBeltHead(EMPTY, "-", RIGHT))
                return number1-number2;
        
        while(true){
            while(belt.get(indexHead) == "1" || belt.get(indexHead) == "=" || belt.get(indexHead) == "-" ){
                        moveBeltHead("1","1",RIGHT);
                        moveBeltHead("=","=",RIGHT);
                        moveBeltHead("-","-",RIGHT);
            }
            
            if(!moveBeltHead(EMPTY, "1", LEFT))
                break;
            
            while(belt.get(indexHead) == "1" || belt.get(indexHead) == "=" || belt.get(indexHead) == "-" ){
                        moveBeltHead("1","1",LEFT);
                        moveBeltHead("=","=",LEFT);
                        moveBeltHead("-","-",LEFT);
            }
            
            if(!moveBeltHead("Y", "Y", RIGHT))
                break;
            
            if(!moveBeltHead("1", "Y", RIGHT))
                break;
        }
        
        return number1-number2;
    }
    
    public int multiplication(int number1, int number2){
        addEqual();
        
        while(true){
            if(!moveBeltHead("1",X,RIGHT))
                        break;

            while(belt.get(indexHead) == "1" ){
                    moveBeltHead("1", "1", RIGHT);
            }
           
            if(!moveBeltHead("*","*",RIGHT))
                        break;
            
            while(true){
                 

                if(!moveBeltHead("1","Y",RIGHT))
                            break;
                
                while(belt.get(indexHead) == "1" ){
                        moveBeltHead("1", "1", RIGHT);
                }

                if(!moveBeltHead("=","=",RIGHT))
                            break;

                while(belt.get(indexHead) == "1" ){
                        moveBeltHead("1", "1", RIGHT);
                }
                
                
                if(!moveBeltHead(EMPTY,"1",LEFT))
                            break;
                
                
                while(belt.get(indexHead) == "1" || belt.get(indexHead) == "=" ){
                        moveBeltHead("1", "1", LEFT);
                        moveBeltHead("=","=",LEFT);
                }
                if(!moveBeltHead("Y","Y",RIGHT))
                            break;
            }
            
            if(!moveBeltHead("=","=",LEFT))
                            break;
            
            while(belt.get(indexHead) == "1" || belt.get(indexHead) == "*"  || belt.get(indexHead) == "Y"  ){
                        moveBeltHead("1", "1", LEFT);
                        moveBeltHead("*","*",LEFT);
                        moveBeltHead("Y","1",LEFT);
            }
            
            if(!moveBeltHead(X,X,RIGHT))
                            break;
        }
        
        return number1*number2;
    }
    
    public String division(int number1, int number2){ 
        
        addEqual(); 
        while(true){
            
           
            while(true){
                while(belt.get(indexHead) == "1" || belt.get(indexHead) == "X"  || belt.get(indexHead) == "Z"  ){
                            moveBeltHead("1", "1", RIGHT);
                            moveBeltHead("X","X",RIGHT);
                            moveBeltHead("Z","Z",RIGHT);
                }

                if(!moveBeltHead("/","/",RIGHT))
                                break;
                
                 
                if(moveBeltHead("=","=",RIGHT)){
                   
                    moveBeltHead("#","E",RIGHT);
                    moveBeltHead("#","R",RIGHT);
                    moveBeltHead("#","R",RIGHT);
                    moveBeltHead("#","O",RIGHT);
                    moveBeltHead("#","R",RIGHT);
                    return "ERROR";
                }
                
                if(!moveBeltHead("Y","Y",RIGHT))
                     if(!moveBeltHead("1","1",NOT))
                                break;
                while(belt.get(indexHead) == "Y"){
                            moveBeltHead("Y", "Y", RIGHT);
                }
                
                if(!moveBeltHead("1","Y",LEFT))
                                break;
                
                while(belt.get(indexHead) == "/" || belt.get(indexHead) == "X"  || belt.get(indexHead) == "Z" || belt.get(indexHead) == "Y" ){
                            moveBeltHead("/","/",LEFT);
                            moveBeltHead("X","X",LEFT);
                            moveBeltHead("Z","Z",LEFT);
                            moveBeltHead("Y","Y",LEFT);
                }
                
                if(!moveBeltHead("1","X",RIGHT))
                                break;
            }
            
            if(!moveBeltHead("=","=",RIGHT))
                                break;
            
            while(belt.get(indexHead) == "1"){
                            moveBeltHead("1", "1", RIGHT);
            }
            
            if(!moveBeltHead(EMPTY,"1",LEFT))
                                break;
            
            while(belt.get(indexHead) == "1"){
                            moveBeltHead("1", "1", LEFT);
            }
            
            if(!moveBeltHead("=","=",LEFT))
                                break;
            
            while(belt.get(indexHead) == "/" || belt.get(indexHead) == "X"  || belt.get(indexHead) == "Z" || belt.get(indexHead) == "Y" ){
                            moveBeltHead("/","/",LEFT);
                            moveBeltHead("X","Z",LEFT);
                            moveBeltHead("Z","Z",LEFT);
                            moveBeltHead("Y","1",LEFT);
            }
            if(!moveBeltHead("#","#",RIGHT))
                     if(!moveBeltHead("1","1",NOT))
                                break;
            
             
        }
         
        if(!moveBeltHead("#","#",RIGHT))
                return ""+-1;
        
        if(!moveBeltHead("X","X",NOT))
                return ""+(float)number1/number2;
        
        while(belt.get(indexHead) == "/" || belt.get(indexHead) == "X"  || belt.get(indexHead) == "Z" || belt.get(indexHead) == "Y"|| belt.get(indexHead) == "1" || belt.get(indexHead) == "="){
                            moveBeltHead("/","/",RIGHT);
                            moveBeltHead("X","X",RIGHT);
                            moveBeltHead("Z","Z",RIGHT);
                            moveBeltHead("Y","Y",RIGHT);
                            moveBeltHead("1","1",RIGHT);
                            moveBeltHead("=","=",RIGHT);
            }
        if(!moveBeltHead("#","R",LEFT))
                return ""+-2;
        while(true){
            while(belt.get(indexHead) == "/" || belt.get(indexHead) == "X"  || belt.get(indexHead) == "Z" || belt.get(indexHead) == "Y"|| belt.get(indexHead) == "1" || belt.get(indexHead) == "=" || belt.get(indexHead) == "R"){
                            moveBeltHead("/","/",LEFT);
                            moveBeltHead("X","X",LEFT);
                            moveBeltHead("Z","Z",LEFT);
                            moveBeltHead("Y","Y",LEFT);
                            moveBeltHead("1","1",LEFT);
                            moveBeltHead("=","=",LEFT);
                            moveBeltHead("R","R",LEFT);
            }
        
            if(!moveBeltHead("#","#",RIGHT))
                    break;
            
            while(belt.get(indexHead) == "Z"){
                            moveBeltHead("Z", "Z", RIGHT);
            }
            
            if(!moveBeltHead("X","Z",RIGHT))
                    break;
            
            while(belt.get(indexHead) == "/" || belt.get(indexHead) == "X"  || belt.get(indexHead) == "Z" || belt.get(indexHead) == "Y"|| belt.get(indexHead) == "1" || belt.get(indexHead) == "=" || belt.get(indexHead) == "R"){
                            moveBeltHead("/","/",RIGHT);
                            moveBeltHead("X","X",RIGHT);
                            moveBeltHead("Z","Z",RIGHT);
                            moveBeltHead("Y","Y",RIGHT);
                            moveBeltHead("1","1",RIGHT);
                            moveBeltHead("=","=",RIGHT);
                            moveBeltHead("R","R",RIGHT);
            }
            
            if(!moveBeltHead("#","1",LEFT))
                    break;
        }
        
        return ""+(float)number1/number2;
    }
    public ArrayList getBelt() {
        return belt;
    }

    public void setBelt(ArrayList belt) {
        this.belt = belt;
    }
    
    @Override
    public String toString() {
        return ""+ belt;
    }

    public ArrayList<ArrayList> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<ArrayList> moves) {
        this.moves = moves;
    }

    public ArrayList<ArrayList> getMovements() {
        return movements;
    }

    public void setMovements(ArrayList<ArrayList> movements) {
        this.movements = movements;
    }
    
  
    
}
