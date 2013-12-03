/* Generated By:JavaCC: Do not edit this line. SQLParser.java */
package com.LogVisualization.Parser;
public class SQLParser implements SQLParserConstants {
        public static void main(String args[]) throws ParseException
        {
                SQLParser parser = new SQLParser(System.in);
                while (true)
            {
                parser.parseOneLine();
            }
        }

  final public String parseOneLine() throws ParseException {
    String a;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SELECT:
    case HELP:
    case LS:
    case SET:
      a = expr();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case EOL:
        jj_consume_token(EOL);
        break;
      case 0:
        jj_consume_token(0);
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
                                  {if (true) return a;}
      break;
    case EOL:
      jj_consume_token(EOL);
      break;
    case 0:
      jj_consume_token(0);
                          System.exit(-1);
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String expr() throws ParseException {
  String s;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HELP:
      s = help();
             {if (true) return s;}
      break;
    case SET:
      s = set();
             {if (true) return s;}
      break;
    case LS:
      s = ls();
            {if (true) return s;}
      break;
    case SELECT:
      s = select();
                {if (true) return s;}
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String select() throws ParseException {
  Token t;
  String fi="",fu="",where="",groupby="",orderby="";
  String FI="|FIELDS ";
  String FU="";
  String r="search ";
  String index=" index=";
  String step="";
    jj_consume_token(SELECT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
    case ASTERISK:
      fi = fields();
                             FI+=fi;
      break;
    case COUNT:
    case MAX:
    case MIN:
      fu = func();
                                                FI+=fu;FU+=fu;
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        break label_1;
      }
      t = jj_consume_token(COMMA);
                                                                           FI+=t.toString();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ID:
      case ASTERISK:
        fi = fields();
                                                                                                          FI+=fi;
        break;
      case COUNT:
      case MAX:
      case MIN:
        fu = func();
                                                                                                                             FI+=fu;FU+=" "+fu;
        break;
      default:
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(FROM);
                                                                                                                                                          index+=identifier();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case WHERE:
      where = where();
      break;
    default:
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case GROUP:
      groupby = groupby();
      break;
    default:
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ORDER:
      orderby = orderby();
      break;
    default:
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STEP:
      step = step();
      break;
    default:
      ;
    }
          if (index != "index=")
          {
                r+=index+" ";
          }
          if (where != "")
          {
                r+=where;
          }
          if(FU!="")
          {
            r+="|STATS "+FU;
            if (groupby!="")
                {
                        r+=" "+groupby;
                }

          }
          if(orderby!="")
          {
                  r+=orderby;
          }
          r+=FI;
          if(step!="")
          {
            r+=" "+step;
          }
          {if (true) return r;}
    throw new Error("Missing return statement in function");
  }

  final public String where() throws ParseException {
  String result="";
  String s="";
  Token t = null;
    if (jj_2_1(2)) {
      jj_consume_token(WHERE);
      s = condition();
                                         result+=s;
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case AND:
        case OR:
          ;
          break;
        default:
          break label_2;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case AND:
          t = jj_consume_token(AND);
          s = condition();
                                                                                      result+=" "+(t.toString()).toUpperCase()+" "+s;
          break;
        case OR:
          t = jj_consume_token(OR);
          s = condition();
                                                                            result+=" "+(t.toString()).toUpperCase()+" "+s;
          break;
        default:
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
         {if (true) return result;}
    } else if (jj_2_2(2)) {
      jj_consume_token(WHERE);
      s = between();
                                 {if (true) return s;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String condition() throws ParseException {
  String s1, s2, s3;
  Token t1, t2, t3;
    if (jj_2_3(2)) {
      s1 = attribute();
      t1 = jj_consume_token(LIKE);
      s2 = pattern();
                                                 {if (true) return s1+t1.toString()+s2;}
    } else if (jj_2_4(3)) {
      s1 = attribute();
      t1 = jj_consume_token(EQUAL);
      t2 = jj_consume_token(FLOATING_POINT_LITERAL);
                                                                 {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_5(4)) {
      s1 = attribute();
      t1 = jj_consume_token(EQUAL);
      s2 = time();
                                               {if (true) return s1+t1.toString()+s2.toString();}
    } else if (jj_2_6(3)) {
      s1 = attribute();
      t1 = jj_consume_token(EQUAL);
      t2 = jj_consume_token(INTEGER_LITERAL);
                                                          {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_7(2)) {
      s1 = attribute();
      t1 = jj_consume_token(EQUAL);
      t2 = jj_consume_token(ASTERISK);
                                                   {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_8(3)) {
      s1 = attribute();
      t1 = jj_consume_token(LESSEQUAL);
      t2 = jj_consume_token(FLOATING_POINT_LITERAL);
                                                                     {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_9(4)) {
      s1 = attribute();
      t1 = jj_consume_token(LESSEQUAL);
      s2 = time();
                                                   {if (true) return s1+t1.toString()+s2;}
    } else if (jj_2_10(3)) {
      s1 = attribute();
      t1 = jj_consume_token(LESSEQUAL);
      t2 = jj_consume_token(INTEGER_LITERAL);
                                                              {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_11(3)) {
      s1 = attribute();
      t1 = jj_consume_token(LESS);
      t2 = jj_consume_token(FLOATING_POINT_LITERAL);
                                                                {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_12(4)) {
      s1 = attribute();
      t1 = jj_consume_token(LESS);
      s2 = time();
                                              {if (true) return s1+t1.toString()+s2;}
    } else if (jj_2_13(3)) {
      s1 = attribute();
      t1 = jj_consume_token(LESS);
      t2 = jj_consume_token(INTEGER_LITERAL);
                                                         {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_14(3)) {
      s1 = attribute();
      t1 = jj_consume_token(GREATEREQUAL);
      t2 = jj_consume_token(FLOATING_POINT_LITERAL);
                                                                        {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_15(4)) {
      s1 = attribute();
      t1 = jj_consume_token(GREATEREQUAL);
      s2 = time();
                                                      {if (true) return s1+t1.toString()+s2;}
    } else if (jj_2_16(3)) {
      s1 = attribute();
      t1 = jj_consume_token(GREATEREQUAL);
      t2 = jj_consume_token(INTEGER_LITERAL);
                                                                 {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_17(3)) {
      s1 = attribute();
      t1 = jj_consume_token(GREATER);
      t2 = jj_consume_token(FLOATING_POINT_LITERAL);
                                                                   {if (true) return s1+t1.toString()+t2.toString();}
    } else if (jj_2_18(4)) {
      s1 = attribute();
      t1 = jj_consume_token(GREATER);
      s2 = time();
                                                 {if (true) return s1+t1.toString()+s2;}
    } else if (jj_2_19(3)) {
      s1 = attribute();
      t1 = jj_consume_token(GREATER);
      t2 = jj_consume_token(INTEGER_LITERAL);
                                                            {if (true) return s1+t1.toString()+t2.toString();}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String between() throws ParseException {
  Token t1,t2;
  Token bet, and;
  String s;
    s = identifier();
    bet = jj_consume_token(BETWEEN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      t1 = jj_consume_token(INTEGER_LITERAL);
      break;
    case FLOATING_POINT_LITERAL:
      t1 = jj_consume_token(FLOATING_POINT_LITERAL);
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    and = jj_consume_token(AND);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      t2 = jj_consume_token(INTEGER_LITERAL);
      break;
    case FLOATING_POINT_LITERAL:
      t2 = jj_consume_token(FLOATING_POINT_LITERAL);
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
          {if (true) return s+">="+t1.toString()+" "+and.toString()+" "+s+"<="+t2.toString();}
    throw new Error("Missing return statement in function");
  }

  final public String orderby() throws ParseException {
  String s, r="";
  Token t;
    jj_consume_token(ORDER);
    jj_consume_token(BY);
    s = attribute();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASC:
      t = jj_consume_token(ASC);
                                   r+="|SORT "+s;
      break;
    case DESC:
      t = jj_consume_token(DESC);
                                                            r+="|SORT -"+s;
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
                                                                              {if (true) return r;}
    throw new Error("Missing return statement in function");
  }

  final public String func() throws ParseException {
  String r="";
  String s="";
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case COUNT:
      t = jj_consume_token(COUNT);
      jj_consume_token(OPENPAREN);
      s = identifier();
      jj_consume_token(CLOSEPAREN);
                                                       {if (true) return r+=t.toString()+"("+s+")";}
      break;
    case MAX:
      t = jj_consume_token(MAX);
      jj_consume_token(OPENPAREN);
      s = identifier();
      jj_consume_token(CLOSEPAREN);
                                                {if (true) return r+=t.toString()+"("+s+")";}
      break;
    case MIN:
      t = jj_consume_token(MIN);
      jj_consume_token(OPENPAREN);
      s = identifier();
      jj_consume_token(CLOSEPAREN);
                                                {if (true) return r+=t.toString()+"("+s+")";}
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String groupby() throws ParseException {
  String r="";
  String s="";
  Token t;
    jj_consume_token(GROUP);
    t = jj_consume_token(BY);
    s = identifier();
   {if (true) return t.toString()+" "+s;}
    throw new Error("Missing return statement in function");
  }

  final public String fields() throws ParseException {
        String r="";
        Token t;
        String s="";
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ASTERISK:
      t = jj_consume_token(ASTERISK);
                     {if (true) return r=t.toString();}
      break;
    case ID:
      s = identifier();
                 r=s;{if (true) return r;}
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String help() throws ParseException {
  Token t;
    t = jj_consume_token(HELP);
              {if (true) return t.toString();}
    throw new Error("Missing return statement in function");
  }

  final public String ls() throws ParseException {
  String s;
    jj_consume_token(LS);
    s = ls_operand();
                     {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public String loginout() throws ParseException {
  String s;
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LOGOUT:
      t = jj_consume_token(LOGOUT);
              {if (true) return t.toString();}
      break;
    case LOGIN:
      jj_consume_token(LOGIN);
      s = identifier();
                       {if (true) return s;}
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String set() throws ParseException {
  String s1;
    jj_consume_token(SET);
    ls_operand();
    jj_consume_token(EQUAL);
    s1 = identifier();
                                          {if (true) return s1.toString();}
    throw new Error("Missing return statement in function");
  }

  final public String ls_operand() throws ParseException {
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CHARTTYPE:
      t = jj_consume_token(CHARTTYPE);
                  {if (true) return t.toString();}
      break;
    case DATASOURCE:
      t = jj_consume_token(DATASOURCE);
                   {if (true) return t.toString();}
      break;
    case FIELDS:
      t = jj_consume_token(FIELDS);
               {if (true) return t.toString();}
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String attribute() throws ParseException {
  String s;
    s = identifier();
                   {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public String pattern() throws ParseException {
  Token t1, t2,t3;
  String s;
    t1 = jj_consume_token(LQUOTATION);
    s = identifier();
    t3 = jj_consume_token(LQUOTATION);
          {if (true) return t1.toString()+s+t3.toString();}
    throw new Error("Missing return statement in function");
  }

  final public String time() throws ParseException {
  String month,slash,day,inter_literal,colon,hour,minute,second;
  Token slash_t,day_t,int_t,colon_t,hour_t,minute_t,second_t;
  String result="";
    day_t = jj_consume_token(INTEGER_LITERAL);
    slash_t = jj_consume_token(SLASH);
    month = month();
    jj_consume_token(SLASH);
    int_t = jj_consume_token(INTEGER_LITERAL);
    colon_t = jj_consume_token(COLON);
    hour_t = jj_consume_token(INTEGER_LITERAL);
    jj_consume_token(COLON);
    minute_t = jj_consume_token(INTEGER_LITERAL);
    jj_consume_token(COLON);
    second_t = jj_consume_token(INTEGER_LITERAL);
   slash = slash_t.toString();
   day = day_t.toString(); result += day;
   result += slash;
   result+=month;
   result += slash;
   inter_literal = int_t.toString(); result += inter_literal;
   colon = colon_t.toString(); result += colon;
   hour = hour_t.toString(); result += hour;
   result += colon;
   minute = minute_t.toString(); result += minute;
   result += colon;
   second = second_t.toString(); result += second;
   {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public String month() throws ParseException {
  Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case JAN:
      t = jj_consume_token(JAN);
              {if (true) return t.toString();}
      break;
    case FEB:
      t = jj_consume_token(FEB);
               {if (true) return t.toString();}
      break;
    case MAR:
      t = jj_consume_token(MAR);
               {if (true) return t.toString();}
      break;
    case APR:
      t = jj_consume_token(APR);
               {if (true) return t.toString();}
      break;
    case MAY:
      t = jj_consume_token(MAY);
               {if (true) return t.toString();}
      break;
    case JUN:
      t = jj_consume_token(JUN);
               {if (true) return t.toString();}
      break;
    case JUL:
      t = jj_consume_token(JUL);
               {if (true) return t.toString();}
      break;
    case AUG:
      t = jj_consume_token(AUG);
               {if (true) return t.toString();}
      break;
    case SEP:
      t = jj_consume_token(SEP);
               {if (true) return t.toString();}
      break;
    case OCT:
      t = jj_consume_token(OCT);
               {if (true) return t.toString();}
      break;
    case NOV:
      t = jj_consume_token(NOV);
               {if (true) return t.toString();}
      break;
    case DEC:
      t = jj_consume_token(DEC);
               {if (true) return t.toString();}
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String identifier() throws ParseException {
  Token t;
    t = jj_consume_token(ID);
               {if (true) return t.toString();}
    throw new Error("Missing return statement in function");
  }

  final public String step() throws ParseException {
        Token t,t1,t2;
        String s="";
    t = jj_consume_token(STEP);
    jj_consume_token(EQUAL);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      t1 = jj_consume_token(INTEGER_LITERAL);
      break;
    case FLOATING_POINT_LITERAL:
      t1 = jj_consume_token(FLOATING_POINT_LITERAL);
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case HOUR:
      t2 = jj_consume_token(HOUR);
      break;
    case Min:
      t2 = jj_consume_token(Min);
      break;
    case SEC:
      t2 = jj_consume_token(SEC);
      break;
    default:
      jj_consume_token(-1);
      throw new ParseException();
    }
          s+=t.toString();
          s+="=";
          s+=t1.toString();
          s+=t2.toString();
          {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_15(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_16(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_16(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_17(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_17(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_18(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_18(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_2_19(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_19(); }
    catch(LookaheadSuccess ls) { return true; }
  }

  private boolean jj_3_9() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(LESSEQUAL)) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3R_7() {
    if (jj_scan_token(ID)) return true;
    return false;
  }

  private boolean jj_3R_6() {
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    if (jj_scan_token(SLASH)) return true;
    return false;
  }

  private boolean jj_3_8() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(LESSEQUAL)) return true;
    if (jj_scan_token(FLOATING_POINT_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_7() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(EQUAL)) return true;
    return false;
  }

  private boolean jj_3_6() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(EQUAL)) return true;
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_5() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(EQUAL)) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_4() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(EQUAL)) return true;
    if (jj_scan_token(FLOATING_POINT_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_3() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3_7()) {
    jj_scanpos = xsp;
    if (jj_3_8()) {
    jj_scanpos = xsp;
    if (jj_3_9()) {
    jj_scanpos = xsp;
    if (jj_3_10()) {
    jj_scanpos = xsp;
    if (jj_3_11()) {
    jj_scanpos = xsp;
    if (jj_3_12()) {
    jj_scanpos = xsp;
    if (jj_3_13()) {
    jj_scanpos = xsp;
    if (jj_3_14()) {
    jj_scanpos = xsp;
    if (jj_3_15()) {
    jj_scanpos = xsp;
    if (jj_3_16()) {
    jj_scanpos = xsp;
    if (jj_3_17()) {
    jj_scanpos = xsp;
    if (jj_3_18()) {
    jj_scanpos = xsp;
    if (jj_3_19()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3_3() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(LIKE)) return true;
    return false;
  }

  private boolean jj_3_1() {
    if (jj_scan_token(WHERE)) return true;
    if (jj_3R_3()) return true;
    return false;
  }

  private boolean jj_3_2() {
    if (jj_scan_token(WHERE)) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3R_5() {
    if (jj_3R_7()) return true;
    return false;
  }

  private boolean jj_3R_4() {
    if (jj_3R_7()) return true;
    return false;
  }

  private boolean jj_3_19() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(GREATER)) return true;
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_18() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(GREATER)) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_17() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(GREATER)) return true;
    if (jj_scan_token(FLOATING_POINT_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_16() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(GREATEREQUAL)) return true;
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_15() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(GREATEREQUAL)) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_14() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(GREATEREQUAL)) return true;
    if (jj_scan_token(FLOATING_POINT_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_13() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(LESS)) return true;
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_12() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(LESS)) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_11() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(LESS)) return true;
    if (jj_scan_token(FLOATING_POINT_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_10() {
    if (jj_3R_5()) return true;
    if (jj_scan_token(LESSEQUAL)) return true;
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public SQLParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;

  /** Constructor with InputStream. */
  public SQLParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SQLParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new SQLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Constructor. */
  public SQLParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SQLParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
  }

  /** Constructor with generated Token Manager. */
  public SQLParser(SQLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  /** Reinitialise. */
  public void ReInit(SQLParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      return token;
    }
    token = oldToken;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    Token errortok = token.next;
    int line = errortok.beginLine, column = errortok.beginColumn;
    String mess = (errortok.kind == 0) ? tokenImage[0] : errortok.image;
    return new ParseException("Parse error at line " + line + ", column " + column + ".  Encountered: " + mess);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}