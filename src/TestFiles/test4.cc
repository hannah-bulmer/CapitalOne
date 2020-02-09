#include <iostream>
#include <string>
#include <sstream>
using namespace std
/**
 * Code taken from my old CS assignment
 * To test counting comments
 * **/


//takes a string and makes it an int
// Very cool
int stringToInt (string s) {
  istringstream sock(s);
  int n;
  sock >> n;
  return n;
}

// removes extra whitespace from a text
void removeWhitespace (string &text) {
  for(int i = text.size()-1; i >= 0; i-- ) {
    if (text[i] == ' ' && text[i] == text[i-1]) {
      text.erase(text.begin() + i );
    }
  }
}

// gives you the next word but doesn't remove it from OG text
string seeNextWord (string &text) {
  string s;
  for (unsigned i = 0; i < text.size(); i ++) {
    if (text[i] == ' ') {
      break;
    }
    else s += text[i];
  }
  return s;
}
