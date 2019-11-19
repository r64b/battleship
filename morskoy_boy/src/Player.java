public class Player implements States{  
    private Field field;
    private Ship[] ships;
    int points;
    
    Player(){
        this.field = new Field();
        this.ships = new Ship[10];
        this.points=0;
    }
    
    boolean placeShip(int x0, int y0, int x1, int y1, int place){
        Ship new_ship = new Ship(x0, y0, x1, y1);
        if(!new_ship.isLinear())
            return false;
        
        ships[place] = new_ship;
        
        int X0=x0, Y0=y0, X1=x1, Y1=y1;
        if(x0>x1){
            X0=x1;
            X1=x0;
        }
        if(y0>y1){
            Y0=y1;
            Y1=y0;
        }
        
        for(int xP=X0;xP<=X1;xP++){
            for(int yP=Y0;yP<=Y1;yP++){
                this.field.setPointState(xP, yP, BUSY);                         
            }
        }   
        return true;
    }
    
    Field getField(){
        return this.field;
    }
    
    int getShipLenght(int place){
        int lenght = this.ships[place].lenght();
        return lenght;
    }
    
    void draw(boolean hidden){
        this.field.drawField(hidden);
    }
    
    boolean isWon(){
        if(this.points>=10){
            return true;
        }
        return false;
    }
    
    int fire(Field enemy_field, int x, int y, boolean explosion){
        int state = enemy_field.getPointState(x, y);
        switch(state){
            case BUSY:{
                enemy_field.setPointState(x, y, DROWN);
                return DROWN;
            }
            case EMPTY:{
                enemy_field.setPointState(x, y, MISSED);
                return MISSED;                
            }
            case DROWN:{
                if(explosion){
                    enemy_field.setPointState(x, y, BLOWN);
                    return BLOWN;
                }       
            }/*
            case EXPLODED:{
                if(explosion){
                    enemy_field.setPointState(x, y, BLOWN);
                    return BLOWN;
                }      
            }*/
        }
        return 10;
    }
    
    void blowUp(Player shooting_player){
        for(int i=0;i<this.ships.length;i++){
            this.ships[i].Explode(shooting_player, this);
        }
    }
    
    int shoot(Player enemy_player, int x, int y){
        int hit = this.fire(enemy_player.getField(), x, y, false);
        enemy_player.blowUp(this);
        return hit;
    }
    
    boolean isLost(Player shooting_player){
        for(int i=0;i<this.ships.length;i++){
            //this.ships[i].Explode(shooting_player, this);
            if(!this.ships[i].isBlown(this.field)){
                return false;
            }
        }
        return true;
    }
}
