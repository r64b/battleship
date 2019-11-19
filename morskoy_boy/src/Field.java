public class Field implements States{    
    private int[][] field = new int[10][10];    
///////////////////////////////////////////////////////////////
    int getPointState(int column, int row){
        if((column<0)||(column>9)||(row<0)||(row>9)){
           return 10; 
        }
                
        int point = this.field[column][row];
        return point;
    }
    
    void setPointState(int column, int row, int state){
        this.field[column][row] = state;
    }
///////////////////////////////////////////////////// ///////////
    Field(){
        for(int c = 0;c<10;c++){
            for(int r = 0;r<10;r++){
                this.setPointState(c, r, EMPTY);
            }
        }
    }
    Field(int state){
        for(int c = 0;c<10;c++){
            for(int r = 0;r<10;r++){
                this.setPointState(c, r, state);
            }
        }
    }
////////////////////////////////////////////////////////////////
    void drawField(boolean hidden){
        System.out.println(" ABCDEFGHIJ");
        System.out.println(" 0123456789");
        for(int c = 0;c<10;c++){
            String row = new String();
            row = row+c;
            for(int r = 0;r<10;r++){
                if(!hidden){
                    row = row+this.field[c][r];
                }
                else{
                    if((this.field[c][r]==BUSY)||(this.field[c][r]==EMPTY)){
                        row = row + HIDDEN;
                    }
                    else{
                        row = row+this.field[c][r];
                    }                    
                }
            }
            System.out.println(row);
        }
    }
////////////////////////////////////////////////////////////////
    
}
