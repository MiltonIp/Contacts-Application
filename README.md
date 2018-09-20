# Why is the data stored in txt instead of JSON?

This was made a long, long time ago before I knew that JSON was the standard for exchanging data

# Contacts-Application
An application that can add, edit, view and delete contacts


Format of text file:

Line 1: type of contact ("Friend", "Family" or "Business")

Line 2: 

For Friend: Context E.g. classmate from CS 452 

For Family: Relationship E.g. First cousin once removed
        
For Business: -title- | -company- E.g. Software Engineer | Pied Piper
        
Line 3: Name of contact 

Line 4: Address of contact

Line 5: Email of contact

Line 6: Number of contact

Line 7: Unique ID of contact to differentiate between contacts with same info


EXAMPLE TEXT FILE:

Business                              - Line 1

Software Engineer|Pied Piper          - Line 2

John Smith                            - Line 3

1 Heather Dr.                         - Line 4

email@email.net                       - Line 5

123-543-1222                          - Line 6

772                                   - Line 7

Family                                - Line 1

Grandmother                           - Line 2

Heather Jones                         - Line 3

99 Homith St.                         - Line 4

heathersmith@email.com                - Line 5

999-999-9999                          - Line 6

122                                   - Line 7

Friend                                - Line 1

Met at soccer club                    - Line 2

Alex Man                              - Line 3

123 Address Crescent                  - Line 4

manly@email.ca                        - Line 5

000-000-0000                          - Line 6

151                                   - Line 7

