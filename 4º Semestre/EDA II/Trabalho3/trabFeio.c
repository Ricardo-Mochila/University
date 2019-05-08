#include <stdio.h>

int main(void)
{
    int numTeams = 0;
    int numGames = 0;
    char* teamName[20];
    
    scanf("%d %d", &numTeams, &numGames);
    
    for(int i = 0; i < numTeams; i++)
    {
        scanf("%s", teamName[i]);
        
    }

    /*for(int i = 0; i < numGames; i++)
    {
        scanf("%s %d - %s %d", teamName1, &golo1, teamName2, &golo2);
    }

    return 0;*/

    return 0;
}