public class Ship {
    private int[][] position = new int[2][2];                       // [0][] - для x,  [1][] - для y
///////////////////////////////////////////////////////////////
    int lenght(){                                                   // функции для определения характеристик корабля
        int x, y;
        x = 1 + this.position[0][1] - this.position[0][0];
        y = 1 + this.position[1][1] - this.position[1][0];
        if(x>y){
            return x;
        }
        else{
            return y;
        }
    }
    boolean isHorizontal(){        
        int x, y;
        x = 1 + this.position[0][1] - this.position[0][0];
        y = 1 + this.position[1][1] - this.position[1][0];
        if(x>y){
            return true;
        }
        else{
            return false;
        }
    }
    boolean isLinear(){
        if(this.position[0][0]==this.position[0][1]){
            return true;
        }
        else{
            if(this.position[1][0]==this.position[1][1]){
            return true;
            }
        }
        return false;
    }
    
    private void minToMax(){
        if(this.position[0][0]>this.position[0][1]){
            int buf = this.position[0][0];
            this.position[0][0]=this.position[0][1];
            this.position[0][1]=buf;
        }
        if(this.position[1][0]>this.position[1][1]){
            int buf = this.position[1][0];
            this.position[1][0]=this.position[1][1];
            this.position[1][1]=buf;
        }            
    }
///////////////////////////////////////////////////////////////
    Ship(int x0, int y0, int x1, int y1){        
            this.position[0][0]=x0;
            this.position[1][0]=y0;
            this.position[0][1]=x1;
            this.position[1][1]=y1;  
            this.minToMax();
    }
///////////////////////////////////////////////////////////////
    boolean isBlown(Field current_field){
        if(this.isHorizontal()){
            int count=0;
            for(int x = this.position[0][0];x<=this.position[0][1];x++){
                if((current_field.getPointState(x, this.position[1][0])==Field.DROWN)||(current_field.getPointState(x, this.position[1][0])==Field.BLOWN)){
                    count++;
                }
            }
            if(count==this.lenght()){
                return true;
            }
        }
        else{
            int count=0;
            for(int y = this.position[1][0];y<=this.position[1][1];y++){
                if((current_field.getPointState(this.position[0][0], y)==Field.DROWN)||(current_field.getPointState(this.position[0][0], y)==Field.BLOWN)){
                    count++;
                }
            }
            if(count==this.lenght()){
                return true;
            }
        }
        return false;
    }
    void Explode(Player shooting_player, Player current_player){
        if(this.isBlown(current_player.getField())){
            //System.out.println("Boom!");
            shooting_player.points++;
            if(this.isHorizontal()){
                    //int count=0;
                for(int x = this.position[0][0];x<=this.position[0][1];x++){
                    for(int xP=-1;xP<=1;xP++){
                        for(int yP=-1;yP<=1;yP++){
                            int X = x+xP;
                            if((X<0)||(X>9)){
                                continue;
                            }
                            int Y = this.position[1][0]+yP;
                            if((Y<0)||(Y>9)){
                                continue;
                            }
                            shooting_player.fire(current_player.getField(), X, Y, true);
                            //if(shooting_player.fire(current_player.getField(), X, Y, true)==0){
                            //current_player.getField().setPointState(X, Y, Field.EXPLODED);
                                //current_player.blowUp(shooting_player);
                            //current_player.getField().setPointState(X, Y, Field.BLOWN);
                            //}
                        }
                    }
                        //if(current_field.getPointState(x, this.position[1][0])==Field.DROWN){
                        //    count++;
                        //}
                }
                    //if(count==this.lenght()){
                    //    return true;
                //}
            }
            else{
                //int count=0;
                for(int y = this.position[1][0];y<=this.position[1][1];y++){
                    for(int xP=-1;xP<=1;xP++){
                        for(int yP=-1;yP<=1;yP++){
                            int X = this.position[0][0]+xP;
                            if((X<0)||(X>9)){
                                continue;
                            }
                            int Y = y+yP;
                            if((Y<0)||(Y>9)){
                                continue;
                            }
                            shooting_player.fire(current_player.getField(), X, Y, true);
                            //if(shooting_player.fire(current_player.getField(), X, Y, true)==0){  
                            //current_player.getField().setPointState(X, Y, Field.EXPLODED);
                                //current_player.blowUp(shooting_player);                          
                            //current_player.getField().setPointState(X, Y, Field.BLOWN);
                            //}
                        }
                    }   
                        //if(current_field.getPointState(this.position[0][0], y)==Field.DROWN){
                        //    count++;
                        //}
                }
                    //if(count==this.lenght()){
                    //    return true;
                //}
            }
        }
    }
}

/*
механика выстрела:
1. пользователь вводит координаты х,у
2. они идут в функцию стрельба(..х,у..)
3. при попадании, стрельба() меняет значение точки [x][y] с ЗАНЯТ на ЗАТОПЛЕН
4. проверяются все корабли на предмет условия: все точки корабля затоплены - идет взрыв()
5. при взрыве(), во все точки вокруг [x][y] идет выстрел(), при этом сам корабль оказывается ВЗОРВАН

*/