/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudbus.cloudsim.workflow;

import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.power.models.PowerModelSpecPower_REIMS;

/**
 *
 * @author tguerout
 */
public class TestEstimationEnergy {
    double total=0;
    int flag=0;
    
    PowerModelSpecPower_REIMS model = new PowerModelSpecPower_REIMS();
    boolean pooling;

    
    public TestEstimationEnergy()
    {
        pooling = Boolean.parseBoolean(Properties.POOLING.getProperty());
    }
    
    public double getTaskEnergyConsumed(Task ti, double endTime,  boolean opt, String dvfs)
    {
       
        
        //double execTime = ti.getCloudlet().getCloudletLength()/1000;
        double execTime = ti.getCloudlet().getFinishTime()-ti.getCloudlet().getExecStartTime();
        double powerFull,powerIdle;
        int count=0;
        
        powerFull= model.getPMax(model.getnbFreq()-1); // OK si la tache app au CP
        powerIdle= model.getPMin(model.getnbFreq()-1); // OK si la tache app au CP 
        String k="5 4 3 2 1";
        if(pooling)
            powerIdle = powerFull;
        
        if(!ti.isCritical() && opt)
        {
           
                if(dvfs.equalsIgnoreCase("ondemand"))
                {
                    powerFull = model.getPMax(model.getnbFreq()-1);
                    powerIdle = model.getPMin(0);
                    if(pooling)
                        powerIdle = powerFull;
                }
                else
                {
                    powerFull = model.getPMax(ti.getOptIndexFreq());
                    powerIdle = model.getPMin(ti.getOptIndexFreq());
                    if(pooling)
                        powerIdle = powerFull;
                }            
        }
        if(ti.getId()<6) {
        Log.printLine("task : " + ti.getId()  +" full power occupied by task"+ (powerIdle+ti.getId())+ " => power idle/power full : " + powerIdle+"/"+powerFull + " time of execution= " + execTime);
        //count++;
        double Ex = (execTime*powerFull + (endTime-execTime)*powerIdle)/60;
        total=total+(Ex/3600);
        Log.printLine("Energy optimised by task "+ti.getId()+" ="+(Ex/3600)+" Joule");
        }
        
       
        double E = execTime*powerFull + (endTime-execTime)*powerIdle;
        if(ti.getId()==5) 
        {
        	Log.printLine("Total Energy Optimised by all tasks "+k +" in Cloudsim ="+ total);
        	//flag=1;
        }
        return E/3600;
    
    }
    
/*    public void main(String[] args)
    {
    
    }*/
    public boolean isPooling() {
        return pooling;
    }
}
