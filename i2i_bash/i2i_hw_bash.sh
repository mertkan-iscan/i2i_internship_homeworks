#!/bin/bash

# print menu
echo "Please choose an operation:"
echo "+ (Addition)"
echo "- (Subtraction)"
echo "* (Multiplication)"
echo "/ (Division)"
echo "% (Modulo)"
echo "! (Factorial)"
read operator

# ! takes 1 number to calculate factorial
if [ "$operator" = "!" ]
then

    echo "Please enter a number:"
    read number1
    result=1

    while [ $number1 -gt 0 ] # while number grater than 0
    do
        result=$((result*number1))
        number1=$((number1-1))
    done

else
    # take numbers
    echo "Please enter the first number:"
    read number1

    echo "Please enter the second number:"
    read number2

    case $operator in
        '+') result=$((number1+number2)) ;;

        '-') result=$((number1-number2)) ;;

        '*') result=$((number1*number2)) ;;

        '/') 
            if [ $number2 -ne 0 ] # check number2 is not equal to 0
            then

                result=$((number1/number2))

            else # if number2 equal to 0

                echo "Division by zero is not allowed!"
                exit 1

            fi ;;

        '%') 
            if [ $number2 -ne 0 ]
            then

                result=$((number1%number2))

            else

                echo "Division by zero is not allowed!"
                exit 1

            fi ;;
    esac
fi

# print the result
echo "The result is $result"
