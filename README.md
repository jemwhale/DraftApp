# Draft App

The Draft App is a command line application that runs in the terminal. It lets you organise a live draft for your fantasy sports league.

## Instillation

You will need Java installed on your machine to run the Draft App. To check if you already have java installed on your machine open the terminal and enter:

```bash
java -version
```

If details of the JRE (Java Runtime Environment) are listed then you are ready to launch the app. If not then you will need to download and install the latest JRE or JDK (Java Development Kit). Full instructions for this can be found [HERE](https://www.oracle.com/java/technologies/downloads/#jdk19-linux).

## Launching the Draft App

Locate and download the [DraftApp](https://pip.pypa.io/en/stable/) jar file.
In the project repository, this is located here:

```bash
out/artifacts/DraftApp_jar/DraftApp.jar
```

Once you have a local copy saved, open your terminal and change directory into the file's location by using its file path:

```bash
cd #path
```

To open and run the Draft App enter:

```bash
java -jar DraftApp.jar
```

## Using the App

The Draft app will load with some seeded data to enable you to use all the features. You can also create your own data from within the app. 

How to start your own draft (follow the menu commands within the app):
* Create a new league
* Create some teams to join that league - __*Teams always need to belong to an existing league*__
* Create a draft for your league - __*Drafts always need to belong to an existing league*__
* Enter your newly created draft and use the list of commands to navigate and make picks!

### App data

This app is still in development and is not connected to a back end. As such, any data you create with the app will be lost once you close the app.

## Closing the App

enter 

```bash
q
```
at the main menu to close the app. 