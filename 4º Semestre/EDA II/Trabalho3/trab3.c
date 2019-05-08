#include <stdio.h>
#include <string.h>

struct team
{
    char teamName[21];
    int scored;
    int conceded;
    int gamesWon;
    int gamesDraw;
    int gamesLoss;
    int points; 
};


void result(int numTeams, struct team team[])
{
    int max = 0;
    int index = 0;
    for(int i = 0; i < numTeams; i++)
    {
        if(team[i].points > max){
            max = team[i].points;
            index = i;
        }
    }
    for(int i = 0; i < numTeams; i++)
    {
        if(team[i].points == max && i != index){
            printf("torneio sem vencedora\n");
            return;
        }
    }
    printf("a vencedora foi %s, com %d ponto(s)\nganhou %d jogo(s), empatou %d jogo(s) e perdeu %d jogo(s)\nmarcou %d golo(s) e sofreu %d golo(s)\n", team[index].teamName, team[index].points, team[index].gamesWon, team[index].gamesDraw, team[index].gamesLoss, team[index].scored, team[index].conceded);

}

void teamScores(int j, int scoredA, int scoredB, struct team team[])
{
    team[j].scored += scoredA;
    team[j].conceded += scoredB;
    if(scoredA > scoredB)
    {
        team[j].points += 3;
        team[j].gamesWon += 1;
    }
    else if(scoredB > scoredA)
    {
        team[j].gamesLoss += 1;
    }
    else
    {
        team[j].points += 1;
        team[j].gamesDraw +=1;
    }
    
    
}

void game(int numTeams, int numGames, struct team team[])
{
    int scoredA = 0;
    int scoredB = 0;
    char teamA[21];
    char teamB[21];

    for(int i = 0; i < numGames; i++)
    {
        scanf("%s %d - %s %d",teamA, &scoredA, teamB, &scoredB);
        for(int j = 0; j < numTeams; j++)
        {
            if(strcmp(teamA, team[j].teamName) == 0)
            {
                teamScores(j, scoredA, scoredB, team);
            }
            else if (strcmp(teamB, team[j].teamName) == 0)
            {
                teamScores(j, scoredB, scoredA, team);
            }
            
        }
    }
    result(numTeams, team);
    
}

void teamInicializer(int numTeams, int numGames)
{
    struct team team[numTeams];

    for(int i = 0; i < numTeams; i++)
    {
        scanf("%s", team[i].teamName);
        team[i].scored = 0;
        team[i].conceded = 0;
        team[i].gamesWon = 0;
        team[i].gamesDraw = 0;
        team[i].gamesLoss = 0;
        team[i].points = 0;
    }

    game(numTeams, numGames, team);

}

int main(void)
{
    int numTeams = 0;
    int numGames = 0;
    
    scanf("%d %d", &numTeams, &numGames);
    teamInicializer(numTeams, numGames);

    return 0;
} 