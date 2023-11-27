package Break;

public class BreakObject {
    private double xBreak;
    private double centerBreakX;
    private double yBreak = 640.0f;

    private final int breakWidth = 130;
    private final int breakHeight = 30;
    private final int halfBreakWidth = getBreakWidth() / 2;
    private boolean BreakMoveAllow = true;

    public double getxBreak() {
        return xBreak;
    }

    public void setxBreak(double xBreak) {
        this.xBreak = xBreak;
    }

    public double getCenterBreakX() {
        return centerBreakX;
    }

    public void setCenterBreakX(double centerBreakX) {
        this.centerBreakX = centerBreakX;
    }

    public double getyBreak() {
        return yBreak;
    }

    public void setyBreak(double yBreak) {
        this.yBreak = yBreak;
    }

    public int getBreakWidth() {
        return breakWidth;
    }

    public int getBreakHeight() {
        return breakHeight;
    }

    public int getHalfBreakWidth() {
        return halfBreakWidth;
    }

    public boolean isBreakMoveAllow() {
        return BreakMoveAllow;
    }

    public void setBreakMoveAllow(boolean breakMoveAllow) {
        BreakMoveAllow = breakMoveAllow;
    }
}
