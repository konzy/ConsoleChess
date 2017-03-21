package Chess;

public class Timer{
    int p1Seconds = 300;
    int p2Seconds = 300;
    Boolean p1turn = true, p2turn = false, delaySet = true, delayFinished = false;
    double delay, currentTime;

    public void turnSwitch(){
        if(p1turn){
            p1turn = false;
            p2turn = true;
            delaySet = true;
            delayFinished = false;
        }
        else{
            p1turn = true;
            p2turn = false;
            delaySet = true;
            delayFinished = false;
        }
    }

    public void countDown(){
        if(p1turn){
            p1Timer();
        }
        else{
            p2Timer();
        }
    }

    void p1Timer(){
        if(delaySet){
            delay = (double) System.currentTimeMillis();
            delay += 5000;
            delay = 0;
            delaySet = false;
        }
        currentTime = (double) System.currentTimeMillis();
        if(currentTime < delay && !delayFinished){
            currentTime = (double) System.currentTimeMillis();
            return;
        }
        if(!delayFinished){
            delayFinished = true;
        }
        if((currentTime - 1000) >= delay){
            p1Seconds--;
            delay = currentTime;
        }
    }

    void p2Timer() {
        if (delaySet) {
            delay = (double) System.currentTimeMillis();
            delay += 5000;
            delay = 0;
            delaySet = false;
        }
        currentTime = (double) System.currentTimeMillis();
        if (currentTime < delay && !delayFinished) {
            currentTime = (double) System.currentTimeMillis();
            return;
        }
        if (!delayFinished) {
            delayFinished = true;
        }
        if ((currentTime - 1000) >= delay) {
            p2Seconds--;
            delay = currentTime;
        }
    }

    public int getP1Time(){
        return p1Seconds;
    }

    public int getP2Time(){
        return p2Seconds;
    }

    public int getCurrentTurn() {
        if(p1turn){
            return 0;
        }
        else{
            return 1;
        }
    }
}