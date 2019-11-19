/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import java.util.Random;
/**
 * МОРСКОЙ БОЙ
 * 
 * 1 - Начать игру
 * 2 - Правила
 * 3 - Выход
 * 
 * игра "морской бой":
 * 1. каждый игрок обладает игровым полем 10х10 "квадратов".
 *    игроки выбирают, как им расположить свои корабли на игровом поле.
 *    у каждого игрока не менее 10 кораблей, общей длиной не менее 20 "квадратов".
 *    корабли могут соприкасаться гранями и пересекаться между собой.
 *    
 * 2. начиная с первого игрока, каждый по очереди "делает выстрел" - выбирает "квадрат" на вражеском поле.
 *    игрок не может повторно "делать выстрел" в один и тот же "квадрат".
 *    если в этом "квадрате" находился корабль, та его часть, которая находилась в этом "квадрате", оказывается "затоплена".
 *    когда все точки корабля "затоплены", то корабль "взрывается", при этом в каждый "квадрат" вокруг корабля проходит "выстрел".
 * 
 * 3. за каждый "взорванный" корабль противника игрок получает одно очко.
 *    игра заканчивается, как только один из игроков наберет 10 очков.
 */
public class MorskoyBoy implements Texts{
    
    static void pText(String x){
        System.out.print(x+"\n");
    }
    static void pLine(String x){
        System.out.println(x);
    }
    static int gNum() throws Exception{
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    
    static int gameCycle(int last_game_state)throws Exception{
        switch(last_game_state){
            ////////////////////////////////////////////////////////////////////////////
            case 0:{//Появление главного меню
                pText(MENU);
                pLine(REQUIRE_COMMAND);
                int input = gNum();
                return gameCycle(input);
            }
            ////////////////////////////////////////////////////////////////////////////
            case 1:{//Новая игра
                Player player1 = new Player();//1 игрок
                Player player2 = new Player();//2 игрок
                ////////////////////////////////////////////////////////////////////////////
                pLine(REQUIRE_SHIP_COORDINATE_BEGIN);//начало расстановки кораблей
                int overall_lenght;
                
                overall_lenght=0;
                do{//1 игрок(пользователь)
                int ships_deployed = 0;
                do{
                    pLine(REQUIRE_SHIP_COORDINATE_1);
                    int x0 = gNum();
                    if((x0<0)||(x0>9)){
                        pLine(INCORRECT);
                        continue;
                    }
                    pLine(REQUIRE_SHIP_COORDINATE_2);
                    int y0 = gNum();
                    if((y0<0)||(y0>9)){
                        pLine(INCORRECT);
                        continue;
                    }
                    pLine(REQUIRE_SHIP_COORDINATE_3);
                    int x1 = gNum();
                    if((x1<0)||(x1>9)){
                        pLine(INCORRECT);
                        continue;
                    }
                    pLine(REQUIRE_SHIP_COORDINATE_4);
                    int y1 = gNum();
                    if((y1<0)||(y1>9)){
                        pLine(INCORRECT);
                        continue;
                    }/*
                    int x0 = (int)Math.rint(Math.random()*10);
                    if((x0<0)||(x0>9)){
                        continue;
                    }
                    int y0 = (int)Math.rint(Math.random()*10);
                    if((y0<0)||(y0>9)){
                        continue;
                    }
                    int x1 = (int)Math.rint(Math.random()*10);
                    if((x1<0)||(x1>9)){
                        continue;
                    }
                    int y1 = (int)Math.rint(Math.random()*10);
                    if((y1<0)||(y1>9)){
                        continue;
                    }*/
                    if(!player1.placeShip(x0, y0, x1, y1, ships_deployed)){
                        pLine(INCORRECT);
                        continue;
                    }
                    overall_lenght += player1.getShipLenght(ships_deployed);
                    ships_deployed++;
                    player1.draw(false);
                    if(ships_deployed<10)
                        {pLine(REQUIRE_SHIP_COORDINATE_NEXT);}
                }
                while(ships_deployed!=10);
                }
                while(overall_lenght<20);
                pLine(REQUIRE_SHIP_COORDINATE_END);
                ////////////////////////////////////////////////////////////////////////////          
                overall_lenght=0;
                do{//2 игрок(компьютер)
                int ships_deployed = 0;
                do{/*
                    pLine(REQUIRE_SHIP_COORDINATE_1);
                    int x0 = gNum();
                    if((x0<0)||(x0>9)){
                        pLine(INCORRECT);
                        continue;
                    }
                    pLine(REQUIRE_SHIP_COORDINATE_2);
                    int y0 = gNum();
                    if((y0<0)||(y0>9)){
                        pLine(INCORRECT);
                        continue;
                    }
                    pLine(REQUIRE_SHIP_COORDINATE_3);
                    int x1 = gNum();
                    if((x1<0)||(x1>9)){
                        pLine(INCORRECT);
                        continue;
                    }
                    pLine(REQUIRE_SHIP_COORDINATE_4);
                    int y1 = gNum();
                    if((y1<0)||(y1>9)){
                        pLine(INCORRECT);
                        continue;
                    }*/
                    int x0 = (int)Math.rint(Math.random()*10);
                    if((x0<0)||(x0>9)){
                        continue;
                    }
                    int y0 = (int)Math.rint(Math.random()*10);
                    if((y0<0)||(y0>9)){
                        continue;
                    }
                    int x1 = (int)Math.rint(Math.random()*10);
                    if((x1<0)||(x1>9)){
                        continue;
                    }
                    int y1 = (int)Math.rint(Math.random()*10);
                    if((y1<0)||(y1>9)){
                        continue;
                    }
                    if(!player2.placeShip(x0, y0, x1, y1, ships_deployed)){
                        //pLine(INCORRECT);
                        continue;
                    }
                    overall_lenght += player2.getShipLenght(ships_deployed);
                    ships_deployed++;
                    player2.draw(false);
                    //if(ships_deployed<10)
                    //    {pLine(REQUIRE_SHIP_COORDINATE_NEXT);}
                }
                while(ships_deployed!=10);
                }
                while(overall_lenght<20);
                pLine(REQUIRE_SHIP_COORDINATE_END);
                ////////////////////////////////////////////////////////////////////////////
                do{//Ходы игроков
                    pLine(TURN_YOU);//ход пользователя
                    
                    pLine(SCORE);
                    pLine(player1.points + "/" + player2.points);  
                    player2.draw(true);                 
                    
                    pLine(REQUIRE_FIRE_COORDINATE_BEGIN);
                    
                    int X = 0;
                    int Y = 0;  
                                        
                    if(player1.isWon()){
                        pLine(VICTORY);
                        break;
                    }
                    if(player2.isWon()){
                        pLine(DEFEAT);
                        break;
                    }
                    
                    do{
                    pLine(REQUIRE_FIRE_COORDINATE_1);
                    X = gNum();
                    if((X<0)||(X>9)){
                        pLine(INCORRECT);
                        continue;
                    }
                    pLine(REQUIRE_FIRE_COORDINATE_2);
                    Y = gNum();
                    if((Y<0)||(Y>9)){
                        pLine(INCORRECT);
                        continue;
                    }/*
                    X = (int)Math.rint(Math.random()*10);
                    if((X<0)||(X>9)){
                        continue;
                    }
                    Y = (int)Math.rint(Math.random()*10);
                    if((Y<0)||(Y>9)){
                        continue;
                    }*/
                    switch(player1.shoot(player2, X, Y)){
                        case Field.DROWN:{
                            //player2.blowUp(player1);
                            pLine(HIT);
                        }
                        case Field.MISSED:{
                            //player2.blowUp(player1); 
                            pLine(MISS);                           
                        }
                        case Field.ERROR:{
                            pLine(INCORRECT);
                            continue;
                        }
                    }
                    
                    pLine(SCORE);
                    pLine(player1.points + "/" + player2.points);
                    //player2.draw(true);                 
                    }
                    while(false);
                    
                    if(player1.isWon()){
                        pLine(VICTORY);
                        break;
                    }
                    ////////////////////////////////////////////////////////////////////////////
                    pLine(TURN_ENEMY);//ход компьютера
                    
                    pLine(SCORE);
                    pLine(player1.points + "/" + player2.points);     
                    player1.draw(false);
                    do{
                    X = (int)Math.rint(Math.random()*10);
                    if((X<0)||(X>9)){
                        continue;
                    }
                    Y = (int)Math.rint(Math.random()*10);
                    if((Y<0)||(Y>9)){
                        continue;
                    }
                    switch(player2.shoot(player1, X, Y)){
                        case Field.DROWN:{
                            //player2.blowUp(player1);
                            pLine(HIT);
                        }
                        case Field.MISSED:{
                            //player2.blowUp(player1); 
                            pLine(MISS);                           
                        }
                        case Field.ERROR:{
                            pLine(INCORRECT);
                            continue;
                        }
                    }
                            /*
                    if(!player2.shoot(player1, X, Y)){
                        pLine(INCORRECT);
                        continue;
                    } */
                    
                    pLine(SCORE);
                    pLine(player1.points + "/" + player2.points);     
                    //player1.draw(false);
                    }
                    while(false);
                    
                    if(player1.isWon()){
                        pLine(DEFEAT);
                        break;
                    }
                }
                while((!player1.isWon())||(!player2.isWon()));   
                return gameCycle(0);
            }
            ////////////////////////////////////////////////////////////////////////////
            case 2:{//Показать правила
                pText(RULES);
                return gameCycle(0);
            }
            ////////////////////////////////////////////////////////////////////////////
            default:{//Выход из игры
                return 0;
            }
        }
    }

    public static void main(String args[])throws Exception{ 
        gameCycle(0);             
    }
}
