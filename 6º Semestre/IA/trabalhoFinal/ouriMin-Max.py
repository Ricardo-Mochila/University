import time
import sys
from random import random
from copy import copy, deepcopy


class Game:
    level1 = 7
    level2 = 11
    level3 = 13
    max_depth = level2

    def __init__(self):
        self.initialize_game()

    def round_selection(self):

        if len(sys.argv) == 2:
            args = str(sys.argv[1])
            if args == '-p':
                return 'A'
            if args == '-s':
                return 'B'  
            else: 
                args = 'Not valid'  
                
        if len(sys.argv) != 2 or args == 'Not valid':    
            choice = input("Choose left (L) or right (R) hand\n")
            value_of_seed = random()
            print(value_of_seed)
            if value_of_seed < 0.5:
                position_of_seed = "L"
            else:
                position_of_seed = 'R'

            if choice == position_of_seed:
                print("You start first")
                return 'A'
            else:
                print("Oponent starts first")
                return 'B'

    def initialize_game(self):
        self.current_state = [[4,4,4,4,4,4],
                              [4,4,4,4,4,4]]
        
        self.player_one_score = 0
        self.player_two_score = 0

        # Player A always plays first
                
        self.player_turn = self.round_selection()

    def draw_board(self):
        for j in range(0, 6):
            if j == 2:
                print('|  ', end=" ")
            elif j == 3:
                if self.player_one_score < 10:
                    print('{}'.format(self.player_one_score), end=" ")
                else: 
                    print('{}'.format(self.player_one_score), end="")
            elif j == 4:
                print('  |', end=" ")
            else:
                print('   ', end=" ")    
            
        print()

        for i in range(0, 2):
            for j in range(0, 6):
                if j == 5:
                    print('| {} |'.format(self.current_state[i][j]), end=" ")
                else:    
                    print('| {}'.format(self.current_state[i][j]), end=" ")
            print()
        

        for j in range(0, 6):
            if j == 2:
                print('|  ', end=" ")
            elif j == 3:
                if self.player_two_score < 10:
                    print('{}'.format(self.player_two_score), end=" ")
                else: 
                    print('{}'.format(self.player_two_score), end="")
            elif j == 4:
                print('  |', end=" ")
            else:
                print('   ', end=" ")    
        print()

         # Determines if the made move is a legal move
    def is_valid(self, px, py):
        if py < 1 or py > 6:
            return False
        
        elif self.current_state[px][py-1] == 1:
            for i in range(0,6): 
                if i != py-1 and self.current_state[px][i] > 1:
                    return False
            return True
        
        elif self.current_state[px][py-1] == 0:
            return False

        count_top = 0
        count_bottom = 0

        for i in range(0,6):
            count_top += self.current_state[0][i]
            count_bottom += self.current_state[1][i]
        
        if count_top == 0 and count_bottom != 0 and px == 1:
            if self.current_state[1][py-1] >= 6-(py-1):
                return True
            else:
                return False
        
        if count_bottom == 0 and count_top != 0 and px == 0:
            if self.current_state[0][py-1]-(py-1) > 0:
                return True
            else:
                return False

        else:
            return True    
    

    def is_end(self):                                                                           #PlayerOne = X, PlayerTwo = O
        # Score win
        if self.player_one_score >= 25:
            return ('X', self.player_one_score, self.player_two_score)
        
        if self.player_two_score >= 25:
            return ('O', self.player_one_score, self.player_two_score)

        count_top = 0
        count_bottom = 0

        for i in range(0,6):
            count_top += self.current_state[0][i]
            count_bottom += self.current_state[1][i]    

        #In cicle end
        if count_bottom == 1 and count_top == 1:
            i = 0
            j = 0
            for i in range(0, 6):
                if self.current_state[0][i] == 1:
                    i = self.current_state[0][i]
                    break

            for j in range(0, 6):
                if self.current_state[1][j] == 1:
                    j = self.current_state[1][j]
                    break
            
            if i + j == 5:
                self.player_one_score += 1
                self.player_two_score += 1

            if self.player_two_score > self.player_one_score:
                return ('O', self.player_one_score, self.player_two_score)
            if self.player_two_score < self.player_one_score:
                return ('AX', self.player_one_score, self.player_two_score)
            else:
                return ('Tied', self.player_one_score, self.player_two_score)    

        
        if self.player_two_score > self.player_one_score:
            return ('AO', self.player_one_score, self.player_two_score)
        if self.player_two_score < self.player_one_score:
            return ('AX', self.player_one_score, self.player_two_score)
        else:
            return ('T', self.player_one_score, self.player_two_score)

            


    def change_table(self, px, py):
        
        value = self.current_state[px][py]
        if px == 0:
            for i in range(py, -1, -1):
                
                if i == py:
                    self.current_state[px][i] -= value
                else:
                    if value > 0:
                        self.current_state[px][i] += 1
                        value -= 1

            while value > 0:
                for i in range(0, 6):
                    if value > 0:
                        self.current_state[1][i] += 1
                        value -= 1
                        if value == 0:
                            if self.current_state[1][i] == 2 or self.current_state[1][i] == 3:
                                for j in range (i, -1, -1):
                                    if self.current_state[1][j] == 2 or self.current_state[1][j] == 3:
                                        self.player_one_score += self.current_state[1][j]
                                        self.current_state[1][j] = 0
                                    else:
                                        break
                                
                                
                for i in range(5, -1, -1):
                    if value > 0:
                        if i != py:
                            self.current_state[0][i] += 1
                            value -= 1
                       

        if px == 1:
            for i in range(py, 6):
                if i == py:
                    self.current_state[1][i] -= value
                else:
                    if value > 0:
                        self.current_state[1][i] += 1
                        value -= 1

            while value > 0:
                for i in range(5, -1, -1):
                    if value > 0:
                        self.current_state[0][i] += 1
                        value -= 1
                        if value == 0:
                            if self.current_state[0][i] == 2 or self.current_state[0][i] == 3:
                                for j in range (i, 6):
                                    if self.current_state[0][j] == 2 or self.current_state[0][j] == 3:
                                        self.player_two_score += self.current_state[0][j]
                                        self.current_state[0][j] = 0
                                    else:
                                        break

                for i in range(0, 6):
                    if value > 0:
                        if i != py:
                            self.current_state[1][i] += 1
                            value -= 1
                       

        
    def max_alpha_beta(self, alpha, beta, depth):  #ganha o O
        maxv = -30
        py = None

        (result,p1, p2) = self.is_end()
        #print(result, p1 , p2)

        if result == 'X':
            return (-25, 0, depth)
        elif result == 'O':
            return (25, 0, depth)
        elif result == 'Tied' :
            return (0, 0, depth)

        if(depth >= self.max_depth):
            if p1 < p2:
                return (p2, 0, depth)
            if p1 > p2:
                return (-p1, 0, depth)
            
        current_table = deepcopy(self.current_state)
        points1 = self.player_one_score
        points2 = self.player_two_score

        for i in range(0, 6):
            if depth < self.max_depth:
                if self.is_valid(1,i+1):
                    self.change_table(1,i)
                    (m, min_i, depth1) = self.min_alpha_beta(alpha, beta, depth + 1)
                    if m >= maxv:
                        maxv = m
                        py = i
                        
                    self.current_state = deepcopy(current_table)
                    self.player_one_score = points1
                    self.player_two_score = points2

                    # Next two ifs in Max and Min are the only difference between regular algorithm and minimax
                    if maxv >= beta:
                        return (maxv, py, depth)

                    if maxv > alpha:
                        alpha = maxv

        return (maxv, py, depth)
    
    def min_alpha_beta(self, alpha, beta, depth):
        minv = 30

        qy = None

        (result,p1, p2) = self.is_end()

        if result == 'X':
            return (-25, 0, depth)
        elif result == 'O':
            return (25, 0, depth)
        elif result == 'Tied' :
            return (0, 0, depth)

        if(depth >= self.max_depth): 
            if p1 < p2:
                return (p2, 0, depth)
            if p1 > p2:
                return (-p1, 0, depth)
            

        current_table = deepcopy(self.current_state)
        points1 = self.player_one_score
        points2 = self.player_two_score

        for i in range(0, 6):
            if depth < self.max_depth:
                if self.is_valid(0,i+1):
                    self.change_table(0,i) 
                    (m, max_i, depth1) = self.max_alpha_beta(alpha, beta, depth + 1)
                    
                    if m <= minv:
                        minv = m
                        qy = i

                    self.current_state = deepcopy(current_table)
                    self.player_one_score = points1
                    self.player_two_score = points2
                    
                    # Next two ifs in Max and Min are the only difference between regular algorithm and minimax
                    if minv <= alpha:
                        return (minv, qy, depth)

                    if minv < beta:
                        beta = minv

        return (minv, qy,depth)
        
    
    def play_alpha_beta(self):
        while True:
            (self.result,p1, p2) = self.is_end()

            if self.result != 'AO' and self.result != 'AX' and self.result != 'T' and self.result != None:
                if self.result == 'X':
                    print('The winner is X!')
                elif self.result == 'O':
                    print('The winner is O!')
                elif self.result == 'Tied':
                    print("It's a tie!")

                self.initialize_game()
                return

            if self.player_turn == 'A':

                while True:
                    
                    py = int(input('Insert the Y coordinate: '))
                    
                    qy = py
                    
                    if self.is_valid(0, py):
                        self.change_table(0, py-1)
                        
                        count_top = 0
                        count_bottom = 0

                        for i in range(0, 6):
                            count_top += self.current_state[0][i]
                            count_bottom += self.current_state[1][i]

                        if count_bottom == 0:
                            self.player_turn = 'A'
                            print(0)
                            break
                        else:
                            self.player_turn = 'B'
                            break
                        
                    else:
                        print('The move is not valid! Try again.')

            else:
                #start = time.time()

                (m, py, depth) = self.max_alpha_beta(-30, 30, 0)

                #end = time.time()
                #print('Evaluation time: {}s'.format(round(end - start, 3)))                #To verify time of answer, just uncomment this lines

                if py == None:
                    while True:
                        posy =int(6*random())
                        if self.is_valid(px, posy+1):
                            py = posy

                print("Min-Max played in: ", py+1)

                self.change_table(1, py)
                count_top = 0
                count_bottom = 0

                for i in range(0, 6):
                    count_top += self.current_state[0][i]
                    count_bottom += self.current_state[1][i]

                if count_top == 0:
                    self.player_turn = 'B'
                    print(0)

                else:
                    self.player_turn = 'A'

                

def main():

    g = Game()
    g.play_alpha_beta()


    if __name__ == "__main__":
        main()

main()