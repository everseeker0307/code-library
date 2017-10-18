package me.wuhao.invoke_kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

/**
 * Created by everseeker on 2017/9/5.
 */
public class InvokeKettle {
    /**
     * 通过java调用kettle转换
     * @param ktrPath: ktr文件路径
     * @param params: 参数值
     */
    public static void invokeTrans(String ktrPath, String...params) {
        try {
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            TransMeta transMeta = new TransMeta(ktrPath);
            Trans trans = new Trans(transMeta);
            trans.execute(params);
            trans.waitUntilFinished();
            if (trans.getErrors() > 0) {
                throw new Exception("Errors during transformation execution!");
            }
        } catch (KettleException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过java调用kettle作业
     * @param kjbPath: kjb文件路径
     * @param paraNames: 参数名字
     * @param paraValues: 参数值
     */
    public static void invokeJob(String kjbPath, String[] paraNames, String[] paraValues) {
        try {
            KettleEnvironment.init();
            JobMeta jobMeta = new JobMeta(kjbPath, null);
            Job job = new Job(null, jobMeta);
            // 向Job脚本传递参数，脚本中获取参数值: ${参数名}
            if (paraNames != null && paraValues != null) {
                for (int i = 0; i < paraNames.length && i < paraValues.length; i++) {
                    job.setVariable(paraNames[i], paraValues[i]);
                }
            }
            job.start();
            job.waitUntilFinished();
            if (job.getErrors() > 0) {
                throw new Exception("Errors during transformation execution!");
            }
        } catch (KettleXMLException e) {
            e.printStackTrace();
        } catch (KettleException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        invokeTrans("/Users/everseeker/GLSC/任务调度平台/kettle-test.ktr", null);
        invokeJob("/Users/everseeker/GLSC/任务调度平台/kettle-test.kjb", null, null);
    }
}
