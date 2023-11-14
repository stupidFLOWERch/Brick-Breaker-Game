package brickGame;


public class GameEngine {

    private OnAction onAction;
    private int fps = 15;
    private Thread updateThread;
    private Thread physicsThread;

    private boolean isTimeThreadRunning = true;
    public boolean isStopped = true;

    public void setOnAction(OnAction onAction) {
        this.onAction = onAction;
    }

    /**
     * @param fps set fps and we convert it to millisecond
     */
    public void setFps(int fps) {
        this.fps = 800/fps;
    }

    private synchronized void Update() {
        updateThread = new Thread(new Runnable() {
            @Override

            public void run() {
                while (!updateThread.isInterrupted()) {
                    try {
                        onAction.onUpdate();
                        Thread.sleep(fps);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        //e.printStackTrace();
                        break;
                    }
                }
            }
        });
        updateThread.start();
    }

    private void Initialize() {
        onAction.onInit();
    }

    private synchronized void PhysicsCalculation() {
        physicsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    try {
                        onAction.onPhysicsUpdate();
                        Thread.sleep(fps);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        //e.printStackTrace();
                        break;
                    }
                }
            }
        });

        physicsThread.start();

    }

    public void start() {
        time = 0;
        Initialize();
        Update();
        PhysicsCalculation();
        TimeStart();
        isStopped = false;
    }

    public void stop() {
        if (!isStopped) {
            isStopped = true;
            updateThread.interrupt();
            physicsThread.interrupt();
            timeThread.interrupt();
            stopTimeThread();
        }
    }

    private long time = 0;

    private Thread timeThread;

    private void TimeStart() {
        timeThread = new Thread(()-> {
            while (isTimeThreadRunning) {
                try {
                    time++;
                    onAction.onTime(time);
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    //e.printStackTrace();
                    break;
                }
            }
        });
        timeThread.start();
    }

    public void stopTimeThread() {
        isTimeThreadRunning = false;
        timeThread.interrupt();
    }

    public interface OnAction {
        void onUpdate();

        void onInit();

        void onPhysicsUpdate();

        void onTime(long time);
    }

}
