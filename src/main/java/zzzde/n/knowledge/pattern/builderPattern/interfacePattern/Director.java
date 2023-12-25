package zzzde.n.knowledge.pattern.builderPattern.interfacePattern;

//具体的操作人员 指挥类
public class Director {
    private Builder mBuilder;

    public void setBuilder(Builder builder){
        this.mBuilder = builder;
    }
    public void createComputer(){
        mBuilder.setCPU();
        mBuilder.setMemery();
        mBuilder.setHardDisk();
        mBuilder.setKeyboard();
        mBuilder.setMouse();
    }
    public Computer getComputer(){
        return mBuilder.getComputer();
    }
}
