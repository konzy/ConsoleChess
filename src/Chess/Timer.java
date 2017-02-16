package Chess;

public class Timer{
    int p1Seconds = 300;
    int p2Seconds = 300;
    Boolean p1turn, p2turn;
    double delay, currentTime, startTime;

    void turnSwitch(){
        if(p1turn){
            p1turn = false;
            p2turn = true;
        }
        else{
            p1turn = true;
            p2turn = false;
        }
    }

    void p1Timer(){
        p1turn = true;
        delay = (double) System.currentTimeMillis();
        delay += 5000;
        currentTime = (double) System.currentTimeMillis();
        while(currentTime < delay){
            currentTime = (double) System.currentTimeMillis();
        }
        startTime = (double) System.currentTimeMillis();
        while(p1turn){
            if((currentTime - 1000) >= startTime){
                p1Seconds--;
                startTime = currentTime;
            }
            else{
                currentTime = (double) System.currentTimeMillis();
            }

        }
    }
}