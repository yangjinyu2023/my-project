package com.example.demo.designPattern.facade;

/** * 外观类 */
public class ComputerFacade {
    private Computer cpu;
    private Computer ddr;
    private Computer ssd;

    /** * 启动cpu */
    public void onCPU() {
        cpu = new Cpu();
        cpu.open();
    }

    /** * 启动内存 */
    public void onDDR() {
        ddr = new Ddr();
        ddr.open();
    }

    /** * 启动硬盘 */
    public void onSSD() {
        ssd = new Ssd();
        ssd.open();
    }
}