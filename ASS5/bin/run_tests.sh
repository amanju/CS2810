#!/bin/bash
# CS2810 Testbench
# Runs tests

TEST_DIR=../tests
TEST_COUNT=5
PROG="java NQueens"
AS_ARGS=0
if [[ $1 = "-g" ]]; then GEN=1; else GEN=0; fi;

cd bin;
j=0
for i in `seq 1 $TEST_COUNT`; do
    echo "Running Test $i... "
    inp="in-$i";
    outp="out-$i";
    outp_="out-${i}_";
    if [[ $AS_ARGS == 1 ]]; then
      $PROG $ARGS `cat $TEST_DIR/$inp` > $TEST_DIR/$outp_;
    else 
      $PROG $ARGS < $TEST_DIR/$inp > $TEST_DIR/$outp_;
    fi
    if [[ $GEN != 0 ]]; then
      mv $TEST_DIR/$outp_ $TEST_DIR/$outp;
      echo "Generated"
    else
      echo "Correct Answer:"
      cat $TEST_DIR/$outp;
      echo "Your Answer:"
      cat $TEST_DIR/$outp_;
      cd ../bin/;
    fi
done;
echo "Done ($j/$i) passed."
cd ..;

