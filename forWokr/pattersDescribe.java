public class pattersDescribe {}

    /**Рефлексия **********************************************************************************************************/
    Исходная постановка задачи: требуется вызвать статический метод
        [private String SqlStatementExecutor.getDriverClassName(DatabaseVendor d)] с параметром
        [SqlStatementExecutor.getDriverClassName(d); d=DatabaseVendor.Oracle;]

        1.Получаем загрузчик классов
        ClassLoader cL = ClassLoader.getSystemClassLoader();
        2.Получаем объект типа [Class]
        String className = "com.amazon.sct.dbloader.SqlStatementExecutor";
        Class cls = cL.loadClass(className);

        3.Формируем объект типа [Method] с использованием нашего класса [cls=SqlStatementExecutor.class]. Указываем название
        метода [name="getDriverClassName"] и список типов параметров, которые он принимает [DatabaseVendor.class].
        Method mGetDriverClassName = cls.getDeclaredMethod("getDriverClassName", DatabaseVendor.class);

        4.У нас метод private. Поэтому открываем к нему доступ
        mGetDriverClassName.setAccessible(true);

        5.Вариант статического метода:
        Вызываем метод c параметром [d=DatabaseVendor.Oracle]. Метод возвращает значение  [String s]. Т.к. метод
        статический, то передаем ему первым параметром [null].
        DatabaseVendor d=DatabaseVendor.Oracle;
        String s = (String) mGetDriverClassName.invoke(null,d);

        6.Вариант нестатического метода:
        Вызываем метод c параметром [d=DatabaseVendor.Oracle]. Метод возвращает значение  [String s]. Т.к. метод
        не статический, то передаем ему первым параметром объект [obj], у которого вызываем метод.
        Object obj = cls.newInstance();
        DatabaseVendor d=DatabaseVendor.Oracle;
        String s = (String) mGetDriverClassName.invoke(obj,d);


/**Шаблон создания задачи  и запуска задачи [Runnable] p.893***********************************************************/
        1.Определяем задачу, для чего реализуем класс, который [implements Runnable]. В методе определяем нужные нам действия
        (задачу), которая будет выполняться в отдельном потоке.
class R implements Runnable {   public void run() {    }  }
2.Запускаем задачу на выполнение.
public static void main (String[] args){  R r = new R();   r.run(); }


/**Шаблон создания DEMON p.904*****************************************************************************************/
        1.Определяем задачу, для чего реализуем класс, который implements Runnable
class SimpleDaemons implements Runnable {   public void run() {    }  }

    public static void main(String[] args) {
        2.Используем традиционный способ выполнения задачи (Runnable) определенной в №1, передавая ее объекту класса Thread()
        Thread daemon = new Thread(SimpleDaemons());  // Передаем задачу (Runnable) для выполнения.
        3.Устанавливаем флаг Демону =  setDaemon(true) перед запуском  задачи.
                daemon.setDaemon(true);
        4.Запускаем задачу.
        daemon.start();
    }

/**Шаблон создания DEMON с помощью фабрики p.905**********Main_02_DaemonFromFactory.java ******************************/
1.Определяем задачу, для чего реализуем класс, который implements Runnable
class SimpleDaemons implements Runnable { public void run(){} }
2.Создаем фабрику для получения потока c устанавливленным флагом Демону =  setDaemon(true) перед запуском  задачи.
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);// Создаем поток, который вернет метод newThread, получающий определение задачи Runnable r
        t.setDaemon(true);       // Устанавливаем флаг Демону =  setDaemon(true) перед запуском  задачи.
        return t;                // Возвращаем поток, сформированный с помощью метода фабрики
    }
}
    public static void main2(String[] args) {
        3.Создаем объект сервиса ExecutorService и передаем ему поток для управления
        ExecutorService exec = Executors.newCachedThreadPool( new DaemonThreadFactory());
        4.Запускаем с помощью метода ExecutorService.execute сервисного класса задачу SimpleDaemons());
        exec.execute(new SimpleDaemons());
    }
/** Усовершенствование: Шаблон создания DEMON с помощью фабрики p.905**********Main_02_DaemonFromFactory.java *********/
5.Определяем свой объект (DaemonThreadPoolExecutor implements ExecutorService), который уже в конструкторе содержит DaemonThreadFactory()
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
    public DaemonThreadPoolExecutor() {
        super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),new DaemonThreadFactory());
    }
}
6.Заменяем фабрику Executors.newCachedThreadPool( new DaemonThreadFactory()) на созданный объект класса DaemonThreadPoolExecutor
        Было :    ExecutorService exec = Executors.newCachedThreadPool( new DaemonThreadFactory());
        Стало:    ExecutorService exec = new DaemonThreadPoolExecutor();


/**Шаблон создания задачи потока без реализации implements Runnable Main_05_SimpleThread_908.java *********************/
        1.Определяем задачу, для чего реализуем класс (SimpleThread), который extends Thread.
        Так как  Thread implements Runnable, то мы опишем задачу в методе run этого класса
class Th extends Thread {
    public Th()   {  start();  } // помещаем в конструкторе сразу запуск потока
    public void run()       {            }
}
2.Создаем объект Th() для запуска задачи
public static void main(String[] args){
        Th t=new Th(); // Если в конструкторе нет запуска потока, то дополнительно  t.start() запускаем поток
        }

/**Шаблон самоуправляемой реализации Runnable  Main_06_SelfManaged_909.java *******************************************/
/** Это позволяет уйти от множественного наследования (если надо наследовать еще один Class), т.к. мы не наследуем
 * extends Thread */
        1.Определяем задачу, для чего реализуем класс, который implements Runnable
class R implements Runnable {
    private Thread t ;
    2.Внутри задачи запускаем поток t = new Thread(this), которому передана ссылка this = (R implements Runnable)
    public R() {  t = new Thread(this); t.start(); }
    public void run() {        }
}
3.Создаем объект (R implements Runnable) для запуска задачи
public static void main(String[] args){    R r=new R();  }

/**Шаблон использования внутреннего класса  Main_07_ThreadVariations_910.java *****************************************/
/**Закрываем доступ к модели формирования потока */
class InnerTh1 {
    private Th t;
    private class Th extends Thread {
        public Th() { start();  }
        public void run() {     }
    }
    public InnerTh1() {  t = new Th();   }
}

/**Шаблон использования БЕЗЫМЯННОГО внутреннего класса  Main_07_ThreadVariations_910.java ****АЛЬТЕРНАТИВА*************/
/**Закрываем доступ к модели формирования потока АЛЬТЕРНАТИВА предыдущему именованному клаcсу InnerTh1*/
class InnerTh2 {
    private Th t;
    public InnerTh2(String name) {
        t = new Thread(name) {   public void run() {     }        };
        t.start();
    }
}

/**Шаблон использования именованной реализации Runnable  Main_07_ThreadVariations_910.java ****************************/
/**Закрываем доступ к модели формирования потока */
class InnerRun1 {
    private R r;
    private class R implements Runnable {
        Thread t;
        public R()        { t = new Thread(this);    t.start();      }
        public void run() {        }
    }
    public InnerR1()      { r = new R();                             }
}

/**Шаблон использования БЕЗЫМЯННОГО внутреннего класса  Main_07_ThreadVariations_910.java ****АЛЬТЕРНАТИВА*************/
/**Закрываем доступ к модели формирования потока АЛЬТЕРНАТИВА предыдущему именованному клаcсу InnerRun1*/
// Используем анонимную реализацию Runnable:
class InnerRun2 {
    private Thread t;
    public InnerRun2() {
        Runnable r =  new Runnable() {  public void run() {} }
        t = new Thread(r);
        t.start();
    }
}

/**Шаблон использования отдельного метода для выполнения кода в потоке  Main_07_ThreadVariations_910.java *************/
class TM {
    private Thread t;
    public TM() { }
    public void runTask() {
        if(t == null) {   t = new Thread(name) {  public void run() {}   };
            t.start();
        }
    }
}

/**Демонстрация ПЕРЕХВАТА ИСКЛЮЧЕНИЙ ИЗ ПОТОКА **brus917***************************************************************/
ПРОБЛЕМА:
        a)создаем поток в котором выбрасываем исключение
public class Ex implements Runnable{  public void run() { throw new RuntimeException(); }  }
b)неудачная попытка перехвата исключения из потока [Ex]
public static void main (String [] args){
        try { ExecutorService e = Executors.newCachedThreadPool();  e.execute(new Ex());    }
        catch(RuntimeException e) {  System.out.println("Попытка перехватить исключение");  }
        }
        РЕШЕНИЕ ПРОБЛЕМЫ!!!:
        1.Создаем задачу [ExNew], в которой создаем поток и выбрасываем исключение, которое требуется перехватить
public class ExNew implements Runnable{  public void run() { Thread  t = Thread.currentThread();
    throw  new RuntimeException(); } }
2.Создаем обработчик для НЕПЕРЕХВАЧЕННОГО ИСКЛЮЧЕНИЯ
class MUEx implements Thread.UncaughtExceptionHandler {
  public void uncaughtException(Thread t, Throwable e) {   System.out.println("Исключение перехвачено +"+e); }
3.Вешаем с помощью фабрики [HTF] на поток [t] обработчик событий [MUEx],методом фабрики [newThread] возращаем поток [t]
class HTF implements ThreadFactory {
  public Thread newThread(Runnable r){ Thread t = new Thread(r); t.setUncaughtExceptionHandler(new MUEx()); return t;} }
4.Запускаем поток [ExNew] через [ExecutorService]. Исключение перехвачено. Проблема РЕШЕНА.
public static void main(...) { ExecutorService e = Executors.newCachedThreadPool(new HTF()); e.execute(new ExNew()); }


/**Демонстрация БЛОКИРОВКИ **brus920***********************************************************************************/
    1.volatile -  это означает, что она может изменяться разными потоками.
    volatile boolean canceled = false;
    2.mutex (mutual exclusion) - эффект взаимного исключения
    3.synchronized(объявление метода)-все остальные задачи не смогут войти в этот метод,пока он выполняется какой-то задачей
    synchronized void f() { }

/**Демонстрация БЛОКИРОВКИ Look **brus926******************************************************************************/
    1.Использование Look позволяет управлять завершением приложения. При возникновении проблем с ключевым словом
    synchronized происходит исключение, но вы не получите возможность выполнить завершающие действия, чтобы сохранить
    корректное состояние системы.
            а)пример с synchronized:
    public synchronized void f() { }
    б)пример с Lock:
    private Lock lock = new ReentrantLock();
    public void f()  {  lock.lock();    try {   return currentEvenValue; } finally {  lock.unlock();   }    }
    Обязательное наличие try, return, finally

/**Критические секции   **brus936**************************************************************************************/
            1.Часть кода, который надо синхронизировать ограничивают синхронизированным блоком
    synchronized(this) {  p.incrementX(); temp = getPair();   }
    2.Чтобы войти в синхронизированную секцию, надо получить блокировку объекта [s]. Объект [s] может быть this.
    Если блокировка установлена другой задачей, вход в секцию запрещается, пока не будет снята блокировка
    3.Блокировка через Критические секции повышает доступ к части кода приложения
    4.Пример с использованием подхода [Критические секции]
    class P {
        public void increment() {
            Pair temp;
            synchronized(s) {  p.incrementX(); temp = getPair();   }
            store(temp);
        }
    }
    5.Пример с использованием подхода [Look]. Аналог критической секции  [lock.lock(); try {} finally { lock.unlock();}]
    class PLook {
        private Lock lock = new ReentrantLock();
        public void increment() {
            Pair temp;
            lock.lock();  try { p.incrementX();  temp = getPair();  } finally { lock.unlock(); }
            store(temp);
        }
    }
    6.Пример синхронизации по объекту. Методы [ds.f()] и [ds.g()] могут выполняться одновременно. Но эксперимент показал,
    что [ds.f()] и [ds2.f()] одновременно не выполняются. Аналогично [ds.f()] и [ds2.g1()] одновременно не выполняются
    class DualSynch { private Object s = new Object();  public synchronized void f(){ } public synchronized void f1(){ }
        public void g(){ synchronized(s) { } }  public void g(){ synchronized(s) { } }
    }
    public static void main(String[] args) { DualSynch ds = new DualSynch(); DualSynch ds2 = new DualSynch();
        new Thread() { public void run() {  ds.f(); ds2.f();  }  }.start();
        ds.g();
    }

    /**Прерывание потока. Interrupt(). Cancel() **brus949******************************************************************/
    public class Main {
        private static ExecutorService exec = Executors.newCachedThreadPool();
        static void test (Runnable r)  { Future<?> f = exec.submit(r);
            f.cancel(true);  // прерываем поток  ЧЕРЕЗ CANCEL()
        }
        public static void main (String [] args) throws InterruptedException {
            test(new SleepBlocked());           // прервать поток можно
            test(new IOBlocked(System.in));     // прервать поток можно
            test(new SynchronizedBlocked() );   // прервать поток нельзя, т.к. он синхронизован
        }
    }
    class SleepBlocked implements Runnable {
        public void run() {try{ sleep(100);} catch  () { sout(" Поток прерван ");  }  sout("Выход из SleepBlocked");   }
    }
    class IOBlocked implements Runnable {   private InputStream in;   public IOBlocked(InputStream is) { in = is;  }
        public void run() { try{ in.read(); } catch  () {  if (Thread.currentThread().isInterrupted()) sout(" Поток прерван ");  }
            sout("Выход из SleepBlocked");   }
    }

    public class SynchronizedBlocked implements  Runnable{  прервать такую задачу нельзя , f() синхронизован
        public synchronized void f() {
            while (true) Thread.yield();
        }
        public SynchronizedBlocked () { new Thread() {  public void run() {
            f();
        }  }.start();  }
        public void run () {   f();  }
    }

    /**Прерывание потока. без Cancel() через shutdownNow(). ОСВОБОЖДЕНИЕ РЕСУРСОВ socketInput.close(); System.in.close(); *brus951*/
    public static void main (String [] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server =  new ServerSocket(8282);
        InputStream socketInput = new Socket("localhost",8282).getInputStream();

        exec.execute(new IOBlocked(socketInput));        exec.execute(new IOBlocked(System.in));
        exec.shutdownNow();             // Прерывание потоков
        socketInput.close();            System.in.close();
    }

/** Блокирование по Мьютексу *brus953**********************************************************************************/
    Определение: Мьютекс - это объект блокировки.
    1.Показано, что метод [f1] может вызывать [f2], а [f2] может вызывать [f1], т.к. это все пренадлежит одной задаче
    public class MultiLock {
        public synchronized  void f1 (int count) {    if (count -- >0) {   f2(count);    }       }
        public synchronized  void f2 (int count) {    if (count -- >0) {   f1(count);    }       }
        public static void main (String [] args) {
            final MultiLock ml = new MultiLock();    new Thread() {public  void run() { ml.f1(10); }  }.start();   }
    }

/** ВЗАИМОДЕЙСТВИЕ МЕЖДУ ЗАДАЧАМИ *brus959*****************************************************************************/
    Определение: Мьютекс - это объект блокировки.
    1.Используем мьютекс для обращения задач по очереди к одному ресурсу.
            2.Используем мьютекс для согласования - обмена сигналами между задачами.
            3.Добавляем механизм приостановки задач

    [notify()], [notifyAll()] - освобождение блокируемого объекта
    [sleep()], [yield()] - не освобождает объект блокировки.
            [wait()] - вход в него останавливает задачу, блокировка снимается. ВЫЗЫВАЕТСЯ ТОЛЬКО В СИНХРОНИЗИРОВАННЫХ МЕТОДАХ (блоках)
            [sleep()] - везде можно вызывать, так как он не манипулирует блокировкой, а [wait()] монипулирует.
    [wait()] - вызов его - это переход в режим ожидания, но при этом разрешается выполнение других синхронизированных операций
    1-ая форма [wait(5000)] - При выполнении метода [wait] блокируемый объект освобождается, выход после окончания времени
    или методами [notify()], [notifyAll()]
            2-ая форма [wait()]     - При выполнении метода [wait] блокируемый объект освобождается, выход методами [notify()], [notifyAll()]


            4A. ВАРИАНТ №1:
    Использование синхронизированной секции      [wait(); -> notify();]
    class Car {
        private boolean waxOn = false;
        public synchronized void waxed()   {   waxOn = true;     notify();   }
        public synchronized void buffed()  {   waxOn = false;    notify();   }
        public synchronized void waitForWaxing()  {   while(waxOn == false)   wait();   }
        public synchronized void waitForBuffing() {   while(waxOn == true)    wait();   }
    }
    4Б. ВАРИАНТ №2:
    Использование  [Lock(); -> Condition();] ВМЕСТО Использование синхронизированной секции  [wait(); -> notify();]
    class Car {
        private Lock lock = new ReentrantLock();
        private Condition cond = lock.newCondition();
        private boolean waxOn = false;
        public              void waxed() { lock.lock();  try{  waxOn = true;  cond.signallAll(); } catch { lock.unlock() }  }
        public synchronized void buffed(){ lock.lock();  try{  waxOn = false; cond.signallAll(); } catch { lock.unlock() }  }
        public synchronized void waitForWaxing()
        { lock.lock();  try{  waxOn = false; cond.awail(); } catch { lock.unlock() }       }
        public synchronized void waitForBuffing()
        { lock.lock();  try{  waxOn = true;  cond.awail(); } catch { lock.unlock() }       }

        /** ВЗАИМОДЕЙСТВИЕ МЕЖДУ ЗАДАЧАМИ BlockingQueue *brus973***************************************************************/
        Использование очередей позволяет игнорировать проблемы синхронизации

/** Обратный отсчет CountDownLatch *brus983****************************************************************************/
        1.Создаем объект [CountDownLatch] и задаем начальную точку для обратного отсчета =100.
        public static void main(String[] args) throws Exception {
            ExecutorService exec = Executors.newCachedThreadPool();
            // Все подзадачи совместно используют один объект CountDownLatch:
            CountDownLatch latch = new CountDownLatch(100);
            2.Запускаем задачи  [WaitingTask(latch)]. Эти задачи будут ожидать, пока [latch.countDown()] не дойдет  до нуля.
            Каждый вызов [latch.countDown()] уменьшает значение счетчика на единицу.  Задачи [WaitingTask(latch)] выйдут из
            состояния ожидания когда счетчик обратного отчета станет равный нулю.
            for(int i = 0; i < 10; i++)    exec.execute(new WaitingTask(latch));
            for(int i = 0; i < SIZE; i++)  latch.countDown();
            exec.shutdown(); // Выход по завершению всех задач
        }
        3.Каждой задаче передается через конструктор объект [CountDownLatch latch]. Задача находится в состоянии ожидания
        [ latch.await()] до тех пор, пока счетчик обратного отсчета не станет равен нулю.
        class WaitingTask implements Runnable {
            private final CountDownLatch latch;
            WaitingTask(CountDownLatch latch)   {    this.latch = latch;     }
            public void run() {             try {    latch.await();          } catch(InterruptedException ex) {  }     }
        }
        4.Порядок работы счетчика таков
        а) создались потоки с задачами [for(int i = 0; i < 10; i++)    exec.execute(new WaitingTask(latch));]
        б) эти задачи стали в положение ожидания [latch.await()].
        в) счетчик путем  [ for(int i = 0; i < SIZE; i++)  latch.countDown();] достиг значения нуля.
                г) задачи вышли из состояния ожидания.

/** CyclicBarrier используется при создании группы параллельно выполняемых задач *brus985******************************/
        1.Общие положения.
                - создаем объект барьера: [barrier=new CyclicBarrier(int a, Runnable r)] где [a] и [r] определены.
        - при создании объект барьера запускает задачу [r]
                - задача [r] содержит объекты [Horse horse = new Horse(barrier)], которые получают объект [barrier].
                - каждый из объектов [Horse(barrier)] - это отдельная задача, которая содержит метод [run()].
                - в методе [run()] как пример, вставляем вызов [barrier.await()] в цикле  [while].
        Это выглядит так -> [run() { while() {barrier.await();}}  ]
                - это приводит к тому, что при выполнении задачи постоянно вызывается  [barrier.await()]. Если он вызван [a] раз,
        задача [r] повторяется

/** Semaphore используются для того, чтоб перед использованием ресурса проверить его доступность. *brus995*************/
        * Примером может служить тележка (общий ресурс) и два работника (потоки java). Один работник наполняет тележку песком.
        В это время второй работник, который перевозит груз и затем разгружает, не может взять тележку и отвезти ее.
        В то же время, если второй работник увез тележку, то первый работник не должен ничего наполнять.
        Ниже приведен небольшой пример программы из 3 классов.
                http://movejava.blogspot.com/2013/06/javautilconcurrentsemaphore.html
                1.  Создаем [Semaphore(1)] с доступом к ресурсу одного объекта и запускаем два потока [new Worker],
        используют которые этот ресурс
        public static void main(String[] args) {
            Semaphore semaphore = new Semaphore(1);
            new Worker(semaphore, "Adder").start();     new Worker(semaphore, "Reducer").start();    }
        2.  Поток получает [Semaphore(1)] и имя работника. Имя работника определяет условие выполнение определенной работы
        public class Worker extends Thread { private Semaphore semaphore; private String workerName;
            public Worker(Semaphore semaphore, String workerName){ this.semaphore=semaphore; this.workerName=workerName;}
            @Override
            public void run() {
                try {
                    semaphore.acquire(); // Устанавливаем разрешение на выполнение работы [Cart.reduceWeight()] или [Cart.addWeight()]
                    for (int i = 0 ; i < 10 ; i++) {  if (workerName=="Adder")  Cart.reduceWeight();
                        if (workerName=="Reducer") Cart.addWeight();     Thread.sleep(10L);  }
                    semaphore.release();   // Снимаем разрешение на выполнение работы [Cart.reduceWeight()] или [Cart.addWeight()]
                } catch (Exception e) { e.printStackTrace(System.err);  }        }    }
        3.  Описание работы    [Cart.reduceWeight()] и [Cart.addWeight()]
        public class Cart { static int weight = 0;
            static void addWeight(){  weight--; }
            static void reduceWeight(){ weight++; }
            static int getWaight(){ return weight;  }    }

        ОБЩАЯ ИДЕЯ: в блоке, ограниченный  [semaphore.acquire()] и [semaphore.acquire()] может быть дан доступ только тому количеству
        объектов, число который задано в конструкторе [Semaphore(1)] (здесь , например один) или , наверное, в semaphore.acquire(2)
        (здесь, например 2).

        ОПИСАНИЕ: Запущен поток [ new Worker(semaphore, "Adder").start()]. Установлено число разрешений для пользования ресурсом
        [new Semaphore(1)]. Следовательно в запущенном потоке выполняется секция [Cart.reduceWeight()] и [Cart.addWeight()].
        Пока она не выполнится, второй поток не сможет в нее войти.

        /** Exchanger Позволяет двум потокам обменяться объектами. *brus998****************************************************/
        public static void main (String [] args)  {
            Exchanger ex = new Exchanger();
            ER eR1 =   new ER(ex, "A");
            ER eR1 =   new ER(ex, "B");
            new Thread(exchangerRunnable1).start();
            new Thread(exchangerRunnable2).start();
        }
        class ExR implements Runnable{
            Exchanger ex = null;        Object o = null;
            public ExchangerRunnable(Exchanger ex, Object o) { this.ex = ex;  this.o = o;  }
            public void run() {  try {  sout("1 this.o="+this.o+"   Thread=" + this);
                this.o = this.ex.exchange(this.o);
                sout("2 this.object="+this.o+"   Thread=" + this);
            } catch (InterruptedException e) {  e.printStackTrace(); } }
        }


        ОПИСАНИЕ: Имеется две задачи (для простоты задачи  одного класса [ER]). Класс [ER] содержит объект в поле [Object o].
        Также [ER] имеет поле [Exchanger ex]. Это поле, которое содержит обменник [ex]. Обменник [ex] имеет информацию об объектах,
        которые надо поменять.  Обмен объектов местами происходит благодаря методу [this.o = this.ex.exchange(this.o);]





        javaFX
/**Модели создания GUI-интерфейса *************************************************************************************/
        1.Использование  FXML-файла,  содержащего описание  дочерних  узлов  корневого  узла  графа  сцены  и  загрузка  его
        с  помощью статического метода  load()  класса  javafx.fxml.FXMLLoader , возвращающего корневой узел графа сцены:

        Parent root = FXMLLoader.load(getClass().getResource("a.fxml"));
        stage.setScene(new Scene(root));

        2.для связывания компонента данных JavaFX Beans с FXML-описанием FXML-контроллер   может   реализовывать
        interface javafx.fxml.Initializable { void  initialize(java.net.URL  l,  java.util.ResourceBundle  r)
            { name.textProperty().bindBidirectional(person.nameProperty()); }
        }
        вызываемый  средой  выполнения  для  инициализации класса FXML-контроллера

        3.Файл ResourceBundle-ресурсов решает задачу локализации строк GUI-интерфейса
        Parent root = FXMLLoader.load(getClass().getResource("a.fxml"));
        ResourceBundle.getBundle("resources.ui"));   // resources-имя пакета;   ui-имя файла (без расширения).

        4.properties-файл:   ключ=значение
                t=Hello

        5.файл FXML-описания    --
        <Label text="%t"/>   // t-ключ из properties-файла

                6.FXML-контроллер  может  использовать аннотацию  @FXML  пакета javafx.fxml для маркирования protected - или  private -
        поля и методы FXML-контроллера, доступные из FXML-описания:
        <Button id="button" layoutX="126" layoutY="90" text="Click Me!"  onAction="#handleButtonAction" fx:id="button" />
        @FXML   private void handleButtonAction(ActionEvent event) { System.out.println("You clicked me!");}
        7.Чтобы не применять аннотацию  @FXML , поля и методы FXML-контроллера, доступные из FXML-описания, делаются public:
        <Button id="button" layoutX="126" layoutY="90" text="Click Me!"  onAction="#handleButtonAction" fx:id="button" />
                (нет аннотации @FXML) public void handleButtonAction(ActionEvent event) { sout.println("You clicked me!");}

        8.FXML-описание   GUI-интерфейса   JavaFX-приложения   имеет   структуру   XML-документа, однако не имеет XML-схемы.
                а)Для использования специфических XML-элементов пространства имен языка FXML в FXML-описание должно быть включено
        объявление пространства имен
        xmlns:fx="http://javafx.com/fxml".
        б)Экземпляр  JavaFX-компонента  создается  в  FXML-описании  с  помощью  тега  импорта и тега, начинающегося с имени
        класса JavaFX-компонента:
        <?import javafx.scene.control.Label?>
        <Label . . ./>
                9.FXML-создание Java-объектов.
                а) Экземпляр хэш-таблицы  java.util.HashMap  создается с помощью тега:       <HashMap item1=". . .", . . ./>
        б) Экземпляр  Java-класса,  имеющего  статический  метод  valueOf() ,
        создается  с  по мощью указания атрибута  fx:value:                       <Double fx:value="0.0"/>
        б)Java-объект, возвращаемый методом класса-фабрики, создается указанием
        атрибута fx:factory:                                                <FXCollections fx:factory="observableArrayList">
        <String fx:value=". . ."/> . . .
        </FXCollections>
/*
    Кроме  того,  для  создания  экземпляров  классов  можно  переопределить  фабрику
    javafx.fxml.JavaFXBuilderFactory ,   используемую   по   умолчанию   для   создания
    JavaFX-объектов. Для этого при вызове в главном классе JavaFX-приложения ста-
    тического  метода  load(java.net.URL  location,  java.util.ResourceBundle  resources,
                            BuilderFactory builderFactory)  класса  javafx.fxml.FXMLLoader  необходимо в качестве
    аргумента  указать  экземпляр  пользовательского  класса,  реализующего  интерфейс
    javafx.util.BuilderFactory .
    Интерфейс  BuilderFactory   имеет  единственный  метод  getBuilder() ,  возвращающий
    экземпляр  класса,  который  реализует  интерфейс  javafx.util.Builder .  Поэтому  не-
    обходимо также создать пользовательский класс, реализующий интерфейс  Builder .
    Интерфейс  Builder  имеет единственный метод  build() , возвращающий Java-объект.
*/

                10.Тег  <fx:include>  языка FXML обеспечивает модульность FXML-описания и указывает  своим  атрибутом  source
        имя  включаемого  FXML-файла,  содержащего  описание создаваемых JavaFX-компонентов.









/** Шаблон MVC ********************************************************************************************************/
        1.Обеспечиваем нужные связи
        Model m = new Model() extends Observable {}     кого слушаем
        View v = new View() implements Observer {}      кто слушает
        Controller c = new Controller (m) implements ActionListener {}   привязываем к компонентам [v] действия от контроллера [c]

                m.addObserver(v);     добавляем слушателей [v], которые ловят изменения  у [m]
                m.addActionListener(c);
        ------------------------------------------------------------
                2.Порядок взаимодействия:
                2.1.[Controller] имея поле [Model m;] при возникновении события, например при начатии кнопки вызывает
        метод [ m.setIncrement()], который меняет данные в модели.
        class Controller implements ActionListener{
            Model m;
            Controller(m) { this.m=m}
            void action () { m.setIncrement() }
        }
        2.2.[Model] в методе [m.setIncrement()]  изменяет данные и вызывает после изменения:
                [setChanged()]- ставит статус для модели "изменена"  и [notifyObservers()] - оповещает Observer-ов, добавленных
        в методе [m.addObserver(v)]. Эти методы доступны благодаря [extends Observable].
        class Model Model m = new Model() extends Observable  {
            Model() { }
        void setIncrement() {
            // Меняем данные и вызываем после изменений
            setChanged(); notifyObservers();
        }
    }
    2.3.Вызов методов  [setChanged()] и [notifyObservers()] приводит к автоматическому вызову  метода [update()]
    class View implements Observer {
        Model m;
        View(m) { this.m=m}
        void update (Observer o, Object args) {
            // Здесь мы меняем представление
        }
    }


/** Шаблон CALLBACK ***************************************************************************************************/
    1.Интерфейс обратного вызова для заданного [COL col] возвращает рассчитанный по указанному алгоритму пользователя [return (CEL)cell]:

    public interface CallBack<COL,CEL> {    CEL call(COL col) {}       } // Сокращения:    COL=TableColumn, CEL=TableCell

    2.Реализация интерфейса
    public class ImageClickCellFactory implements Callback<COL,CEL> {
        public ImageClickCellFactory() {};
        CEL call(COL col) {
            CEL cell = new CEL();  // [CEL cell = new CEL();]- это пример заданного пользователем алгоритм расчета [CEL cell] для входящего
            return cell
        }

        3.Использование  объекта, реализующего [interface CallBack<COL,CEL>]
        public class TableColumnP {
            Callback<COL,CEL> cb;
            boolean click;
            public TableColumnP(Boolean click) {
                this.click = click;
            }
            public void setCellFactory(Callback<COL,CEL> cb)   { this.cb=cb;}    // принимает объект, реализующий интерфейс
            public void doWork() {      if (click==true) cb.call();    }
        }

        4.Пример кода
        public static void main(String[] args) {
            boolean click = true;
            TableColumnP t = new TableColumnP(click);
            ImageClickCellFactory imageClickCellFactory = ImageClickCellFactory();
            t.setCellFactory(imageClickCellFactory);
            t.doWork();     // по вызову этого метода вызывается при определенных условиях метод [CEL call(COL col)]
        }


//region /** Шаблон COMMAND *******************************************************************************************/
        1.Создаем интерфейс, который декларирует, какие мы будем использовать команды.
        interface  Editor {  void save();  void open();  void close();  }
        2.Строим класс, реализующий интерфейс [Editor], в котором реализация команд.
        class MockEditor implements Editor {
            private final List<String> actions = new ArrayList<>();
            @Override    public void save() {        actions.add("save");    show(); }
            @Override    public void open() {        actions.add("open");    show(); }
            @Override    public void close(){        actions.add("close");   }
        }
        3.Создаем интерфейс, который декларирует, что действие(вызов команды) осуществляется в методе [perform]
        interface  Action  {  public  void  perform(); }
        4.Выполнение каждой команды обвернем в class, реадлизующий интерфейс [Action]. Это делается для того, чтобы стандартизи-
        ровать вызов команд. Например, у объекта Action метод типа [Save.perform()] вызовет [editor.save();], а у
        [Open.perform()] вызовет [editor.open();]. Методы [save()],[open()] реализованы внутри класса [Editor].
        class  Save  implements  Action {
            private final Editor editor;
            public Save(Editor editor) {    this.editor=editor; }
            @Override  public void perform() {   editor.save();   }
        }
        5.Создаем класс [Macro], цель которого с помощью   [record] добавить в [List] нужные команды, а с помощью [run] выполнить
                (пробежать) все добавленные в него команды. Это возможно, так как все команды через [Action] обернуты методом [perform],
        который мы вызываем у всех добавленных объектов [Action].
        class  Macro {
            private final List<Action> actions;
            public Macro() { actions = new ArrayList<>();  }
            public void record(Action action) {     actions.add(action);    }
            public void run() {   actions.forEach(Action::perform);    }
        }
        6.Непосредственно три варианта использования шаблона
        public static void main(String [] args) {
            MockEditor editor = new MockEditor();        Macro  macro  =  new  Macro();

            macro.record(new Open(editor)); macro.record(new Save(editor)); macro.record(new  Close(editor)); macro.run();  //1

            macro.record(()->editor.open());macro.record(()->editor.save());macro.record(()->editor.close()); macro.run();  //2

            macro.record(editor::open);     macro.record(editor::save);     macro.record(editor::close);      macro.run();  //3
        }
//endregion

//region/** Шаблон STRATEGY *******************************************************************************************/
        1.Класс реализующий конкретную стратегию, должен реализовывать этот интерфейс. Класс контекста использует этот интерфейс
        для вызова конкретной стратегии
        interface Strategy { int execute(int a, int b);  }
        2.Реализуем алгоритм с использованием интерфейса стратегии
        class ConcreteStrategyAdd implements Strategy {
            public int execute(int a, int b) { sout("Called ConcreteStrategyAdd's execute()");      return a + b; }
        }
        class ConcreteStrategySubtract implements Strategy {
            public int execute(int a, int b) { sout("Called ConcreteStrategySubtract's execute()"); return a - b; }
        }
        3.Класс контекста использующий интерфейс стратегии
        class Context {   private Strategy strategy;   public Context() {}
            public void setStrategy(Strategy strategy) {  this.strategy = strategy;       }
            public int executeStrategy(int a, int b)   {  return strategy.execute(a, b);  }
        }
        4.Тест
        public static void main(String[] args) {
            Context context = new Context();
            context.setStrategy(new ConcreteStrategyAdd());         int resultA = context.executeStrategy(3,4);

            context.setStrategy(new ConcreteStrategySubtract());    int resultB = context.executeStrategy(3,4);

            sout("Result A : " + resultA );       sout("Result B : " + resultB );
        }
//endregion

//region/** Шаблон OBSERVER *******************************************************************************************/
        1.Формируем [interface Observer]
        interface Observer { void update (float temperature, float humidity, int pressure); }
        2.Формируем [interface Observable]
        interface Observable  {
            void registerObserver(Observer o);    void removeObserver(Observer o);
            void notifyObservers();     // метод оповещения
        }
        3.Реализация наблюдаемого объекта
        class WeatherData implements Observable   {
            private List<Observer> observers;
            private float temperature;        private float humidity;        private int pressure;
            public WeatherData()    {    observers = new ArrayList<Observer>();    }

            @Override  public void registerObserver(Observer o)  { observers.add(o); }   // регистрация наблюдателей
            @Override  public void removeObserver(Observer o)    { observers.remove(o); }// удаление наблюдателей
            @Override  public void notifyObservers()   {                                 // оповещение наблюдателей, передавая им данные напряму через метод [update(temperature, humidity, pressure)]
                for (Observer observer : observers)    {  observer.update(temperature, humidity, pressure);  }
            }
            public void setMeasurements(float temperature, float humidity, int pressure) {
                this.temperature = temperature;  this.humidity = humidity;  this.pressure = pressure; // изменяются данные
                notifyObservers();                                                                    // оповещаются наблюдатели
            };
        }
        4.Реализация наблюдателей, реализующих заданный интерфейс [implements Observer]
        class CurrentConditionsDisplay implements Observer  {
            private float temperature;  private float humidity; private int pressure;
            private WeatherData weatherData;
            public CurrentConditionsDisplay(WeatherData weatherData)   {  this.weatherData = weatherData;   weatherData.registerObserver(this);
            }
            @Override  public void update(float temperature, float humidity, int pressure) {
                this.temperature = temperature;    this.humidity = humidity;  this.pressure = pressure;   display();
            }
            public void display() {sout("Сейчас значения: %.1f градусов цельсия и %.1f %% влажности. Давление %d мм рт. ст.\n", temperature, humidity, pressure);}
        }
        5.Тестирование
        public static void main(String[] args)  {
            WeatherData weatherData = new WeatherData();        // наблюдаемое
            CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);    // наблюдатель. Наблюдатель в конструкторе получает наблюдаемое и регистрируется в наблюдаемом.
            weatherData.setMeasurements(29f, 65f, 745);     // изменение данных с последующим оповещением
            weatherData.setMeasurements(39f, 70f, 760);
        }
//endregion

        //region /** Шаблон TEMPLATE_METHOD ************************************************************************************/
        Паттерн Шаблонный метод предназначен как раз для таких ситуаций. Структура общего алгоритма представлена абстрактным классом.
        В нем имеется ряд абстрактных методов, соответствующих конкретным шагам алгоритма; при этом общий для всех реализаций код
        содержится в самом абстрактном классе. Каждый вариант алгоритма реализован в виде конкретного класса, который переопределяет
        абстрактные методы и предоставляет  соответствующую своему назначению реализацию.
                1.Список игр
        enum GameCode { CHESS, MONOPOLY }
        2.Абстрактный класс, реализация абстрактных методов которого будет специфичной для каждого вида игры.
        abstract class Game {
            private int playersAmount;
            protected abstract void initializeGame();
            protected abstract void playGame();
            protected abstract void endGame();
            protected abstract void printWinner();
            public final void playOneGame(int playersAmount){
                setPlayersAmount(playersAmount); initializeGame();  playGame(); endGame(); printWinner(); }
            public void setPlayersAmount(int playersAmount){   this.playersAmount = playersAmount;  }
        }
        3.Реализация конкретной игры. Игра "Шахматы". Специфически только для шахмат реализует методы класса Game.
        class Chess extends Game {
            @Override protected void initializeGame() { * chess specific initialization actions *  }
            @Override protected void playGame()       { * chess specific play actions           *  }
            @Override protected void endGame()        { * chess specific actions to end a game  *  }
            @Override protected void printWinner()    { * chess specific actions to print winner*  }
        }
    }
    4.Реализация конкретной игры. Игра "Монополия". Специфически только для монополии реализует методы класса Game.
    class Monopoly extends Game{
        @Override protected void initializeGame() { * monopoly specific initialization actions*}
        @Override protected void playGame()       { * monopoly specific play actions          *}
        @Override protected void endGame()        { * monopoly specific actions to end a game *}
        @Override protected void printWinner()    { * monopoly specific actions to print winne*}
    }
    5.Тестирование. Класс, показывающий работу шаблона проектирования "Шаблонный метод".
    public class TemplateMethod {
        public static void main (String [] args){
            final GameCode gameCode = GameCode.CHESS;   // выбираем игру. Каждая игра имеет свои реализации для интерфейса, определяющего общие и абстрактынке методы
            Game game;
            switch (gameCode){
                case CHESS      :  game = new Chess();      break;
                case MONOPOLY   :  game = new Monopoly();   break;
                default :          throw new IllegalStateException();
            }
            game.playOneGame(2);  // запускаем все методы игры поочереди, в строго заданной последовательности.
        }
    }
//endregion


//region /** Шаблон PROXY *** designP\src\main\java\horstman\proxy\ProxyTest.java ****************************/
Паттерн PROXY предназначен
Исходные данные:
    а)есть какие-то объекты [value] и [value2]:  Integer value = new Integer(1);  Integer2 value2 = new Integer2(1);
    б)нет класса, который бы содержал методы классов [Integer] и [Integer2]. Но требуется создать объект, который мог бы
        иметь методы классов [Integer] и [Integer2]
Решение lfyyjq ghj,ktvs:
    а)использовать делегирование. Недостаток - если объектов, чьи методы мы должны использовать, тысячи или они
        неизвестны заранее, то это сделать невозможно.
    б)использовать PROXY

1.Получаем объекты, чьи методы надо реализовывать
    Integer value = new Integer(1);  Integer2 value2 = new Integer2(1);
2.Получаем массив [r], содержащий интерфейсы объектов  [value] и [value2]
    Class[] interfaces = value.getClass().getInterfaces();
    Class[] interfaces2 = value2.getClass().getInterfaces();
    Class[] r = new Class[interfaces.length + interfaces.length];
3.Создаем обработчик вызовов методов
    InvocationHandler handler = new TraceHandler(value, value2);
    в нем в методе
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable  { return m.invoke(target, args);  }
    в качестве [target] выступают [value] и [value2]

4.Создаем Object proxy = Proxy.newProxyInstance(null, r, handler);
    Мы можем вызвать у [proxy] методы интерфейсов [r]. При этом, при каждом вызове срабатывает  [InvocationHandler.invoke]
    В этом методе обращаемся к методам [value] и [value2] и возвращаем результат в нужном исполнении




//endregion


    //region
    Паттерн Шаблонный метод предназначен как раз для таких ситуаций. Структура общего алгоритма представлена абстрактным классом.
    В нем имеется ряд абстрактных методов, соответствующих конкретным шагам алгоритма; при этом общий для всех реализаций код
    содержится в самом абстрактном классе. Каждый вариант алгоритма реализован в виде конкретного класса, который переопределяет
    абстрактные методы и предоставляет  соответствующую своему назначению реализацию.
            1.Список игр
    enum GameCode { CHESS, MONOPOLY }
    2.Абстрактный класс, реализация абстрактных методов которого будет специфичной для каждого вида игры.
    abstract class Game {
        private int playersAmount;
        protected abstract void initializeGame();
        protected abstract void playGame();
        protected abstract void endGame();
        protected abstract void printWinner();
        public final void playOneGame(int playersAmount){
            setPlayersAmount(playersAmount); initializeGame();  playGame(); endGame(); printWinner(); }
        public void setPlayersAmount(int playersAmount){   this.playersAmount = playersAmount;  }
    }
    3.Реализация конкретной игры. Игра "Шахматы". Специфически только для шахмат реализует методы класса Game.
    class Chess extends Game {
        @Override protected void initializeGame() { * chess specific initialization actions *  }
        @Override protected void playGame()       { * chess specific play actions           *  }
        @Override protected void endGame()        { * chess specific actions to end a game  *  }
        @Override protected void printWinner()    { * chess specific actions to print winner*  }
    }
}
4.Реализация конкретной игры. Игра "Монополия". Специфически только для монополии реализует методы класса Game.
class Monopoly extends Game{
    @Override protected void initializeGame() { * monopoly specific initialization actions*}
    @Override protected void playGame()       { * monopoly specific play actions          *}
    @Override protected void endGame()        { * monopoly specific actions to end a game *}
    @Override protected void printWinner()    { * monopoly specific actions to print winne*}
}
5.Тестирование. Класс, показывающий работу шаблона проектирования "Шаблонный метод".
public class TemplateMethod {
    public static void main (String [] args){
        final GameCode gameCode = GameCode.CHESS;   // выбираем игру. Каждая игра имеет свои реализации для интерфейса, определяющего общие и абстрактынке методы
        Game game;
        switch (gameCode){
            case CHESS      :  game = new Chess();      break;
            case MONOPOLY   :  game = new Monopoly();   break;
            default :          throw new IllegalStateException();
        }
        game.playOneGame(2);  // запускаем все методы игры поочереди, в строго заданной последовательности.
    }
}
//endregion




/**********************************************************************************************************************/
/** ЛЯМБДА ВЫРАЖЕНИЯ *Уорбэртон ***************************************************************************************/
/**********************************************************************************************************************/
    Лямбда-выражение - это безымянный метод, который служит для передачи поведения из одного места программы  в  другое
    так, будто это данные. Функциональным интерфейсом называется интерфейс с единственным  абстрактным  методом;  он
    используется  в  качестве типа лямбда-выражения.

    1. Examble
    1.1.    button.addActionListener(new ActionListener(){ publiic  void  actionPerformed(ActionEvent  event) {  sout("button  clicked"); } });
    button.addActionListener(event  ->  sout("button  clicked");)
            1.2.    Runnable noArguments = () -> System.out.println("Hello  World");    noArguments.run();
    1.3.    BinaryOperator<Long> add  =  (х, у)  ->  х  +  у;     System.out.println("05add="+add);
    1.4.    ThreadLocal<DateFormatter> formatter = ThreadLocal.withInitial(() -> new DateFormatter(new SimpleDateFormat("dd-MMM-yyyy")));

    2.Ромбовидный оператор [<String,Integer> = <>]
    Map<String,Integer>  diamondWordCounts  =  new  HashMap<>();

    3.   [for  (Track  track  :  album) sout(track);]   эквивалентно записи   [album.forEach(track-> sout(track););]


    p.161 pdf Lambda Группировка данных
    idea p.471
    brus 859 реализация обработчика



    tableColumn.setCellFactory(
            new Callback<TableColumn<cL, String>,TableCell<cL, String>>(){
        @Override
        public TableCell<cL, String> call(TableColumn<cL, String> param) {
            TableCell<cL, String> cell = new TableCell<cL, String>(){
                @Override
                public void updateItem(String item, boolean empty) {
                    if(item!=null){
                        HBox box= new HBox();
                        box.setSpacing(10) ;
                        ScrollPane sp = new ScrollPane();
                        sp.setPrefSize(160,160);
                        ImageView imageview = new ImageView();
                        imageview.setFitHeight(300);
                        sp.setPannable(false);
                        //  imageview.setFitWidth(300);
                        imageview.setImage(new Image("file:"+item ));
                        box.getChildren().addAll(imageview);
                        sp.setContent(imageview);
                        setGraphic(sp);
                    }
                }
            };
            System.out.println(cell.getIndex());
            return cell;
        }

    });














/****Вставка в середину строки файла ресурсов текста вместо  {0} и {1} *******************/
    MessageFormat.format(
            AppInitializer.resourceBundle.getString("ui.connection.dialog.driver.choose.version"),
    valueProductVersion,                    //      Это вместо {0}
    getValueProductVersionBase(dbVendor)),  //      Это вместо {1}
    dbVendor);
    ui.connection.dialog.driver.choose.version = Version of this JDBC driver {0} is not supported. Use {1} or higher.
//---------------------------------------------------------------------------------------------------------------------

    public static void showAlert(Alert.AlertType alertType,
                                 String title,
                                 String header,
                                 String content,
                                 Stage owner) {
        Alert alert = createAlert(alertType, title, header, content, owner);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        alert.getDialogPane().setPrefSize(200,200);
        //alert.getDialogPane().setPrefSize(alert.getDialogPane().getWidth(), alert.getDialogPane().getHeight());
        alert.setX(screenBounds.getWidth() / 2 - alert.getDialogPane().getWidth() / 2);      // pom 2015/12/14
        alert.setY(screenBounds.getHeight() / 2 - alert.getDialogPane().getHeight() - 90 * getScalingFactorY());  // pom 2015/12/14
        alert.showAndWait();
    }

/****Установка ширины окна диалога Dialog******************************************************************************/
    [this]=[WizardView extends Dialog;]
            this.getWizardStage().setMinWidth(1000);
    setResizable(true);

    public Stage getWizardStage() {
        return (Stage) getDialogPane().getScene().getWindow();
    }
//alert.setGraphic(new ImageView(new Image(ReportTreeCell.class.getResource("high.png").toString())));


    /****Связывание ширины размера окна скачкообразно с высотой ***********************************************************/
    DoubleBinding db = new DoubleBinding() {
        { super.bind(App.getMainStage().widthProperty()); }
        @Override
        protected double computeValue() {
            if( GuiUtils.getKoefDPI("s")==1.25 && App.getMainStage().getWidth()<1273) {  return chartMinHeight+22*1.25; }
            if( GuiUtils.getKoefDPI("s")==1.00 && App.getMainStage().getWidth()<1043) {  return chartMinHeight+22;      }
            return chartMinHeight;
        }};
    summaryChart.minHeightProperty().bind(db);


    BooleanBinding createBinding = new BooleanBinding() {
        {
            bind(
                    sourceMetadataViewModel.selectedMetadata,
                    targetMetadataViewModel.selectedMetadata,
                    lastFocusedMetadataOriginProperty());
        }

        @Override
        protected boolean computeValue() {
            return getMenuBindingCondition(ActionValidator.CREATE_REPORT);
        }
    };



    ========================================================================================================================
    Общий подход:
    а) создается LoadMetaDataHandler, который содержит в себе и CallBack и Task. Это удобно с тем, чтобы выделить в
    отдельный {класс-ОБРАБОТЧИК} все, что касается указанной проблемы. Или в  {класс-ОБРАБОТЧИКе} помещаем все в отдельный
    метод {LoadMetaDataHandler.loadMetaData(...){}, как показано в примере.
        б) вызвав этот метод (в примере вызывается через new)
        new LoadMetaDataHandler().loadMetaData(....)
        мы тем самым запускаем и задачу и создаем объект CallBack.
    в)в методе {loadMetaData} мы выполняем следующие действия
    +) создаем объект {LoadMetaDataCallBack}, метод которого {call} будет вызван после завершения выполнения задачи или
    ее отдельной части;
    +) создаем объект {LoadMetaDataTask}. Это непосредственно задача, которую надо выполнить;
    +) создаем случашель, который проверяет состояние задачи {loadMetaDataTask.stateProperty().addListener()}.
    В примере слушатель проверяет два состояния { newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED}
    Если одно из состояний произойдет, то из {Listener}, будет вызван метод {LoadMetaDataCallBack.call(...)},
    который запишет сообщение в лог {Logger.GENERAL.writeError(exception)} .
        г) запускается сама задача в отдельном потоке  {new Thread(loadMetaDataTask).start();}
    д) это все. Структура классов представлена ниже.
        1.   -----------------------------------------------------------------------------------------------------------
        public class LoadMetaDataHandler  {

        public void loadMetaData (DatabaseVendor databaseVendorSource, DatabaseVendor databaseVendorTarget, TreeNode treeNodeSource, TreeNode treeNodeTarget) {
            LoadMetaDataCallBack callback = new LoadMetaDataCallBack();
            LoadMetaDataTask loadMetaDataTask = new LoadMetaDataTask(databaseVendorSource, databaseVendorTarget, treeNodeSource, treeNodeTarget);
            loadMetaDataTask.stateProperty().addListener((observableValue, oldState, newState) -> {
                Logger.GENERAL.write(MessageType.DEBUG, "loadMetaDataTask: " + oldState + " -> " + newState);
                if (newState == Worker.State.SUCCEEDED || newState == Worker.State.FAILED) {
                    //  loadingDialog.hideDialog();
                    callback.call(null,loadMetaDataTask.getException());
                }
            });
            new Thread(loadMetaDataTask).start();
        }
    }
        2.   -----------------------------------------------------------------------------------------------------------
        public class LoadMetaDataCallBack implements ServiceCallback {

            public LoadMetaDataCallBack()  {
            }
            @Override
            public void call(Object result, Throwable exception) {
                if (exception != null) Logger.GENERAL.writeError(exception);
            }
        }
        3.   ----------------------------------------------------------------------------------------------------------

        public class LoadMetaDataTask extends Task<Void> {
            private DatabaseVendor databaseVendorSource;
            private DatabaseVendor databaseVendorTarget;
            private TreeNode treeNodeSource;
            private TreeNode treeNodeTarget;

            public LoadMetaDataTask(DatabaseVendor databaseVendorSource, DatabaseVendor databaseVendorTarget, TreeNode treeNodeSource,TreeNode treeNodeTarget){
                this.databaseVendorSource = databaseVendorSource;
                this.databaseVendorTarget = databaseVendorTarget;
                this.treeNodeSource = treeNodeSource;
                this.treeNodeTarget = treeNodeTarget;
            }

            @Override
            protected Void call() throws Exception {
                FullNameProcessor.setCurrentProcessor(new FullNameProcessor(databaseVendorSource, databaseVendorTarget,treeNodeSource,treeNodeTarget));
                System.gc();
                return  null;
            }
        }
        4.   ----------------------------------------------------------------------------------------------------------

        new LoadMetaDataHandler().loadMetaData(getProjectModel().getSourceVendor(),getProjectModel().getTargetVendor(),
                mainViewModel.getSourceMetadataViewModel().getModel().getTreeNode(),
                mainViewModel.getTargetMetadataViewModel().getModel().getTreeNode());
        ========================================================================================================================

/****Ограничения исключений *brus394 **********************************************************************************/
        1.Сигнатура Метода может содержать объявление исключения {ExceptionA} даже в том случае, если метод никогда не будет возбуждать исключение
        class A {   public void event() throws ExceptionA {} }
        Подобный подход позволяет заставить перехватывать все исключения, которые могут добавляться в переопределение метода
        2.Методу {event()} в {class B} не может возбудить исключения {Exception_IA}, а только {ExceptionA}
        class A {   public void event() throws ExceptionA {} }
        interface IA {  void event() throws Exception_IA {} }
        class B extends A implements IA {}
        3.Ограничения не распространяются на конструктора.
        4.Конструктор класса {B} не может перехватить исключение конструктора класса {А}
        class A {} class B extends A{}




