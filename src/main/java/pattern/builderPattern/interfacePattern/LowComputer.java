package pattern.builderPattern.interfacePattern;

public class LowComputer implements Builder {

    private Computer myLowComputer;

    //构造函数 新建Builder对象时候赋值具体对象
    public LowComputer(){
        this.myLowComputer = new Computer();
    }

    @Override
    public void setCPU() {
        myLowComputer.setCPU("i5");
    }

    @Override
    public void setMemery() {
        myLowComputer.setMemory("8G");
    }

    @Override
    public void setHardDisk() {
        myLowComputer.setHardDisk("8T");
    }

    @Override
    public void setKeyboard() {
        myLowComputer.setKeyboard("罗技");
    }

    @Override
    public void setMouse() {
        myLowComputer.setMouse("雷蛇");
    }

    @Override
    public Computer getComputer() {
        return myLowComputer;
    }

}
