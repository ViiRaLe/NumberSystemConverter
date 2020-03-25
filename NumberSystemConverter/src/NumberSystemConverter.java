/** A simple program that converts a number in input from a numeric system to another of choice and prints it out.
The user has to input the numeric system of the number first, then the number, then the numeric system of choice.
*/
public class NumberSystemConverter
{
    static String convertedNumber = "";

    public static void main(String[] args)
    {
        int inputSystem = GetNumericSystem(args[0]);
        int outputSystem = GetNumericSystem(args[2]);

        String inputNumber = GetInputNumber(args[1], inputSystem);

        if (inputSystem == 10)
        {
            int intNumber = Integer.parseInt(inputNumber);
            FromDecimal(outputSystem, intNumber);
            System.out.println(convertedNumber);
        }
        else if (outputSystem == 10 && inputSystem != 16)
        {
            int intNumber = Integer.parseInt(inputNumber);
            System.out.println(ToDecimal(inputSystem, intNumber));
        }
        else if (outputSystem == 10 && inputSystem == 16)
        {
            System.out.println(ToDecimal16(inputNumber));
        }
        else if (inputSystem != 16)
        {
            int intNumber = Integer.parseInt(inputNumber);
            FromDecimal(outputSystem, ToDecimal(inputSystem, intNumber));
            System.out.println(convertedNumber);
        }
        else
        {
            FromDecimal(outputSystem, ToDecimal16(inputNumber));
            System.out.println(convertedNumber);
        }

    }


    /**Converts a number from the decimal system to any other numeric system.
    @param outputSystem The numeric system the user wants the number to be converted to.
    @param numberToConvert The number that needs to be converted from the decimal system.
    */
    public static int FromDecimal(int outputSystem, int numberToConvert)
    {
        int resto = numberToConvert % outputSystem;
        if (outputSystem == 2 | outputSystem == 8 | (outputSystem == 16 && resto < 10)) convertedNumber = resto + convertedNumber;
        else
        {
            switch (resto)
            {
                case 10:
                    convertedNumber = "A" + convertedNumber;
                    break;

                case 11:
                    convertedNumber = "B" + convertedNumber;
                    break;

                case 12:
                    convertedNumber = "C" + convertedNumber;
                    break;

                case 13:
                    convertedNumber = "D" + convertedNumber;
                    break;

                case 14:
                    convertedNumber = "E" + convertedNumber;
                    break;

                case 15:
                    convertedNumber = "F" + convertedNumber;
                    break;
            }
        }

        if (numberToConvert < outputSystem) return 0;

        return FromDecimal(outputSystem, numberToConvert / outputSystem);
    }


    /**Converts a number from any numeric system to decimal.
    @param inputSystem The numeric system used for the input number
    @param numberToConvert The number that needs to be converted to the decimal system.
    @return convertedNumber The numberToConvert transformed into decimal.
    */
    public static int ToDecimal(int inputSystem, int numberToConvert)
    {
        int convertedNumber = 0;
        int a = numberToConvert;

        for (int i = 0; i < String.valueOf(numberToConvert).length(); i++)
        {
            convertedNumber += (a % 10) * Math.pow(inputSystem, i);
            a /= 10;
        }


        return convertedNumber;
    }


    /**Converts a number from the hexadecimal system to the decimal system.
    @param numberToConvert The number that needs to be converted to the decimal system.
    @return convertedNumber The number converted to the decimal system from the hexadecimal.
    */
    public static int ToDecimal16(String numberToConvert)
    {
        int convertedNumber = 0;
        int j = numberToConvert.length() - 1;

        for (int i = 0; i < numberToConvert.length(); i++)
        {
            char n = numberToConvert.charAt(i);

            if (n <= '9')
            {
                convertedNumber += (n - '0') * Math.pow(16, j);
            }
            else
            {
                switch (n)
                {
                    case 'A':
                        convertedNumber += 10 * Math.pow(16, j);
                        break;

                    case 'B':
                        convertedNumber += 11 * Math.pow(16, j);
                        break;

                    case 'C':
                        convertedNumber += 12 * Math.pow(16, j);
                        break;

                    case 'D':
                        convertedNumber += 13 * Math.pow(16, j);
                        break;

                    case 'E':
                        convertedNumber += 14 * Math.pow(16, j);
                        break;

                    case 'F':
                        convertedNumber += 15 * Math.pow(16, j);
                        break;
                }
            }

            j--;
        }


        return convertedNumber;
    }

    //GET INPUTS FROM THE CONSOLE

    /**Trasforms a string related to the numeric system to an integer representing it.
    @param inputSystem The string that represents the numeric system.
    */
    public static int GetNumericSystem(String inputSystem)
    {
        try
        {
            return Integer.parseInt(inputSystem);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Input System not valid.");
            System.exit(0);
            return -1;
        }
    }


    /**Checks if the inputted number is valid and returns it.
    @param inputNumber The string that represents the integer number to convert.
    @param inputSystem The system used to write the number.
    @return inputNumber The number checked.
    */
    public static String GetInputNumber(String inputNumber, int inputSystem)
    {
        boolean isValid = true;

        if (inputSystem != 16)
        {
            try
            {
                Integer.parseInt(inputNumber);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Number not valid.");
                System.exit(0);
                return "-1";
            }

            return inputNumber;
        }
        else
        {
            inputNumber = inputNumber.toUpperCase();
            for (int i = 0; i < inputNumber.length(); i++)
            {
                if (inputNumber.charAt(i) < '0' || inputNumber.charAt(i) > 'F') isValid = false;
                else if (inputNumber.charAt(i) >= ':' && inputNumber.charAt(i) <= '@') isValid = false;
            }

            if (isValid) return inputNumber;
            else
            {
                System.out.println("Number not valid.");
                System.exit(0);
                return "-1";
            }
        }
    }
}
