// Create a 3x3 game board
const board = [
  ['', '', ''],
  ['', '', ''],
  ['', '', '']
];

// Function to check if a player has won
function checkWin(player) {
  // Check rows
  for (let i = 0; i < 3; i++) {
    if (board[i][0] === player && board[i][1] === player && board[i][2] === player) {
      return true;
    }
  }

  // Check columns
  for (let i = 0; i < 3; i++) {
    if (board[0][i] === player && board[1][i] === player && board[2][i] === player) {
      return true;
    }
  }

  // Check diagonals
  if (board[0][0] === player && board[1][1] === player && board[2][2] === player) {
    return true;
  }
  if (board[0][2] === player && board[1][1] === player && board[2][0] === player) {
    return true;
  }

  return false;
}

// Function to print the game board
function printBoard() {
  for (let i = 0; i < 3; i++) {
    console.log(board[i].join(' | '));
    if (i < 2) {
      console.log('---------');
    }
  }
}

// Function to play a move
function playMove(row, col, player) {
  if (board[row][col] === '') {
    board[row][col] = player;
    return true;
  } else {
    return false;
  }
}

// Main game loop
let currentPlayer = 'X';
let gameOver = false;

while (!gameOver) {
  printBoard();

  // Get the player's move
  const row = parseInt(prompt('Enter the row (0-2):'));
  const col = parseInt(prompt('Enter the column (0-2):'));

  // Play the move
  if (playMove(row, col, currentPlayer)) {
    // Check if the current player has won
    if (checkWin(currentPlayer)) {
      console.log(`Player ${currentPlayer} wins!`);
      gameOver = true;
    } else {
      // Switch to the other player
      currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
    }
  } else {
    console.log('Invalid move! Try again.');
  }
}
