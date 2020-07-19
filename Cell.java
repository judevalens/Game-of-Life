public class Cell{
    // d -> dead | l -> live | e-> empty
    char state;
    char nextState;

    public Cell(char s){

        state = s;
        nextState = s;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public char getNextState() {
        return nextState;
    }

    public void setNextState(char nextState) {
        this.nextState = nextState;
    }

    public void setState(){
        state = nextState;
    }

}