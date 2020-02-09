
// see checkmate.h
bool same_cdr(int a, int b) {
  assert(a >= 0 && a < 64);
  assert(b >= 0 && b < 64);
  if (a % 8 == b % 8) return true; //same col
  if (a / 8 == b / 8) return true; //same row
  if (a % 7 == b % 7) return true; //same diag right
  if (a % 9 == b % 9) return true; //same diag left
  else return false;
}

// see checkmate.h
bool can_capture_king(char board[64]) {
  int king_loc = 0;
  for (int i = 0; i < 64; i ++) {
    if (board[i] == k) king_loc = i;
  }
  for (int i = 0; i < 64; i ++) {
    if (board[i] == q) {
      if (same_cdr(i, king_loc)) {
        return true;
      }
    }
  }
  return false;
}

// move_king(board) moves the king in every possible
// way it can move from its current location, and
// returns true once it finds a location where it
// cannot be attacked by a queen, & false otherwise
static bool move_king(char board[64]) {
  int king_loc = 0;
  bool captured_queen = false;
  for (int i = 0; i < 64; i ++) {
    if (board[i] == k) king_loc = i;
  }
  for (int i = 0; i < moves_length; i ++) {
    int move = moves[i];
    if ((king_loc + move) >= 0 &&
        (king_loc + move) < 64 &&
        same_cdr(king_loc, king_loc + move) ) {
      if (board[king_loc+move] == q) captured_queen = true;
      board[king_loc+move] = k;
      board[king_loc] = empty;
    }
    if (can_capture_king(board)) {
      //print_board(board); TODO!!
      if (king_loc + move >= 0) {
        if (captured_queen) board[king_loc+move] = q;
        else board[king_loc+move] = empty;
      }
      board[king_loc] = k;
    } else {
      return false;
    }
  } return true;
}

// TODO: see checkmate.h
bool is_checkmate(char board[64]) {
  if (can_capture_king(board)) {
    for (int i = 0; i < 8; i ++) {
      if (!move_king(board)) {
        return false;
      }
    }
  }
  return true;
}

