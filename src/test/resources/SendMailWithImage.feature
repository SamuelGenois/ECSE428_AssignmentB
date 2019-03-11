Feature: Sendind an email with an image attachment

    As a Gmail user,
    I want to send an email with an image attachment
    so I can share the image with another email user.

    Scenario Outline: Normal Flow
        Given that I am on a new email page
        When I enter "<address>" as a recipient email address
        And I add "<image>" as an image to the email as an attachment
        And I send the email
        Then the recipient "<address>" receives the email with "<image>" attached

        Examples:
            |                     address |   image |
            | dummyrecipient832@gmail.com | cat.jpg |
            | dummyrecipient833@gmail.com | dog.png |

    Scenario Outline: Alternate Flow
        Given that I am on a new email page
        When I enter "<address1>" and "<address2>" as recipient email addresses
        And I add "<image>" as an image to the email as an attachment
        And I send the email
        Then the recipients "<address1>" and "<address2>" receive the email with "<image>" attached

        Examples:
            |                    address1 |                    address2 |   image |
            | dummyrecipient832@gmail.com | dummyrecipient833@gmail.com | cat.jpg |
            | dummyrecipient833@gmail.com | dummyrecipient832@gmail.com | dog.png |

    Scenario Outline: Error Flow
        Given that I am on a new email page
        When I enter "<address>" as a recipient email address
        And I add "<image>" as an image to the email as an attachment
        And I send the email
        Then I receive an email explaining that the address could not be found

        Examples:
            |                     address |   image |
            | dummyrecipient834@gmail.com | cat.jpg |
            | dummyrecipient834@gmail.com | dog.png |