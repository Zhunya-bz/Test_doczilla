FILE=result
if [ -e "$FILE" ]; then
  rm -fr "$FILE"
fi

javac *.java

java Program
