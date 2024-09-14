package test.util.time;

import java.util.Objects;

public class BenchTime {
    static BenchTime instance = null;
    private long startTime = 0, stopTime = 0;
    boolean isMeasured = false;
    
    /**
     * コンストラクタ<br>
     * <br>
     * インスタンス生成時にタイムの初期化を行う。<br>
     * Privateメソッドとなるため、外部でのインスタンス化を制限する。
     */
    private BenchTime() {
    	isMeasured = false;
    	startTime = stopTime = 0;
    }
    
    /**
     * ベンチタイム計測部品ファクトリーメソッド<br>
     * <br>
     * インスタンスを返す。NULLの時は新規で生成する。
     * @return
     */
    public static BenchTime newInstance (){
    	if(Objects.isNull(instance)) instance = new BenchTime();
    	return instance;
    }
    
    /**
     * クリアメソッド<br>
     * <br>
     * 自身が持つインスタンスを破棄することでタイムのクリアも行う。
     */
    public void clear() {
    	instance = null;
    }
    
    /**
     *　計測開始メソッド<br>
     * 計測を開始する。
     */
    public void start() {
    	isMeasured = true;
    	startTime = System.currentTimeMillis();
    }
    
    /**
     *　計測終了メソッド<br>
     * 計測を終了し、実行時間を返す。
     * 
     * @return 実行時間
     */
    public long stop() {
    	stopTime = System.currentTimeMillis();
    	if (! isMeasured) throw new RuntimeException("The measurement has not been started.");
    	isMeasured = false;
    	return stopTime - startTime;
    }
    
    /**
     * 実行時間表示メソッド<br>
     * <br>
     * 実行時間を表示する。<br>
     */
    public void print() {
    	if (isMeasured) throw new RuntimeException("The measurement has not been ended.");
    	System.out.println(""+(stopTime - startTime)+"ms");
    }
}
