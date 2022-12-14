runTests: compileFiles runBackendDeveloperTests runFrontendDeveloperTests runDataWranglerTests

run: compileFiles
        java runProg

compileFiles:
        javac TrainDataLoader.java
        javac Node.java
        javac AE_Graph.java
        javac TrainBackend.java
        javac Frontend.java
        javac runProg.java

runDataWranglerTests: DataWranglerTests.class
        java -jar junit5.jar --class-path=. --include-classname=.* --select-class=DataWranglerTests

DataWranglerTests.class: TrainDataLoader.java Node.java DataWranglerTests.java
        javac TrainDataLoader.java
        javac Node.java
        javac -cp .:junit5.jar DataWranglerTests.java

runFrontendDeveloperTests: Frontend.java FrontendDeveloperTest.java IFrontend.java ITrainBackend.java TrainBackend.java
        javac Frontend.java
        javac -cp .:junit5.jar FrontendDeveloperTest.java
        java -jar junit5.jar --class-path=. --include-classname=.* --select-class=FrontendDeveloperTest

runBackendDeveloperTests: compile
        java -jar junit5.jar --class-path=. --include-classname=.* --select-class=BackendDeveloperTests

compile: Node.class AE_Graph.class TrainBackend.class DataWranglerTests.class BackendDeveloperTests.class

Frontend.class: Frontend.java
        javac Frontend.java

Node.class: Node.java
        javac Node.java

BDPlaceholderAE.class: BDPlaceholderAE.java
        javac BDPlaceholderAE.java

BDPlaceholderDW.class: BDPlaceholderDW.java
        javac BDPlaceholderDW.java

TrainBackend.class: TrainBackend.java
        javac TrainBackend.java

BackendDeveloperTests.class: BackendDeveloperTests.java
        javac -cp .:junit5.jar BackendDeveloperTests.java

AE_Graph.class:
        javac AE_Graph.java

clean:
        rm *.class

cleanWindows:
        del *.class
