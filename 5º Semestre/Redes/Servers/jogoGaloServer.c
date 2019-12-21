#include <unistd.h>
#include <stdio.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <string.h>
#include <time.h>

#define PORT 1300

void print_ttt(char ttt[]) // prints the board
{
    int i,
        l = 0;

    for (i = 0; i < 9; i++)
    {
        if (ttt[i] == 2)
            printf(" %d ", i + 1);
        else if (ttt[i] == 0)
            printf(" X ");
        else if (ttt[i] == 1)
            printf(" O ");

        if (i == 2 || i == 5 || i == 8)
            printf("\n");
        if (i == 2 || i == 5)
            printf("---+---+---\n");
        if (i == 0 || i == 1 || i == 3 || i == 4 || i == 6 || i == 7)
            printf("|");
    }
    printf("\n");
}

int try_play(char ttt[], int spot, char mark) // tries to make a play
{
    if (ttt[spot] == 2)
    { // blank spot, can play
        ttt[spot] = mark;
        return 1;
    }
    else
    {
        return 0;
    }
}

int check_win(char ttt[]) // checks if someone won
{
    int who = -1;

    if (
        //lines
        ((who = ttt[0]) == ttt[1] && ttt[1] == ttt[2] && who != 2) ||
        ((who = ttt[3]) == ttt[4] && ttt[4] == ttt[5] && who != 2) ||
        ((who = ttt[6]) == ttt[7] && ttt[7] == ttt[8] && who != 2) ||
        //columns
        ((who = ttt[0]) == ttt[3] && ttt[3] == ttt[6] && who != 2) ||
        ((who = ttt[1]) == ttt[4] && ttt[4] == ttt[7] && who != 2) ||
        ((who = ttt[2]) == ttt[5] && ttt[5] == ttt[8] && who != 2) ||
        //diagonals
        ((who = ttt[0]) == ttt[4] && ttt[4] == ttt[8] && who != 2) ||
        ((who = ttt[2]) == ttt[4] && ttt[4] == ttt[6] && who != 2))
    {
        return who;
    }
    else
        return -1;
}

char get_move(char ttt[])
{
    char move = 0;
    char buf[10];

    while (move < '1' || move > '9')
    {
        printf("Your move? -> ");
        fgets(buf, 5, stdin);
        move = buf[0];

        if (!try_play(ttt, move - '1', 1))
        {
            printf("Spot taken, choose another!\n");
            move = 0;
        }
    }

    return move;
}

// board initialization
char tictactoe[9] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

int main(int argc, char const *argv[])
{
    int server_fd, new_socket;
    struct sockaddr_in address;

    int opt = 1; // for setsockopt() SO_REUSEADDR, below
    int addrlen = sizeof(address);
    char buffer[256];

    // Creating socket file descriptor
    if ((server_fd = socket(AF_INET, SOCK_STREAM, 0)) == 0)
    {
        perror("socket failed");
        exit(EXIT_FAILURE);
    }

    // Forcefully attaching socket to the port 1300
    if (setsockopt(server_fd, SOL_SOCKET, SO_REUSEPORT, &opt, sizeof(opt)))
    {
        perror("setsockopt failed");
        exit(EXIT_FAILURE);
    }
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    // Bind the socket to the network address and port
    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0)
    {
        perror("bind failed");
        exit(EXIT_FAILURE);
    }
    if (listen(server_fd, 3) < 0)
    {
        perror("listen failed");
        exit(EXIT_FAILURE);
    }

    // Wait for a connection
    if ((new_socket = accept(server_fd, (struct sockaddr *)&address,(socklen_t *)&addrlen)) < 0)
    {

        printf("---------------Client connected.-------------\n\n");

        print_ttt(tictactoe);

        int userin = 0;

        read(new_socket, buffer, 1);

        //try_play(tictactoe, buffer[0], 0);

        userin = get_move(tictactoe);
        //try_play(tictactoe, userin, 1);

        while (check_win(tictactoe) != 1)
        {
            print_ttt(tictactoe);

            read(new_socket, buffer, 1);

            //try_play(tictactoe, buffer[0], 1);

            userin = get_move(tictactoe);
            //try_play(tictactoe, userin, 1);
        }
    }
    
    close(new_socket);
    return 0;
}