#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Tile.java MsPacman.java Json.java
java Game
