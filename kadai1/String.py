'''
Created on 2022/06/03
自作Stringクラス
@author: so1ya
'''
from kadai1.bm import bm_search

class String(object):

    st = ""
    ch = []

    '''
    【コンストラクタ】
    '''
    def __init__(self, st=""):

        if isinstance(st, str):
            self.st = st
        else:
            self.st = str(st)

        self.ch = list(self.st)

    '''
    【表示メソッド】
    文字のインスタンスを表示
    '''
    def show(self):
        print(self.st)

    '''
    【文字数カウントメソッド】
    文字数を返す
    '''
    def length(self):
        return len(self.st)


    '''
    【文字分割メソッド】
    全ての文字を１文字ずつ分割して、リストに格納した結果を返す
    '''
    def splitEach(self):
        return self.ch


    '''
    【分割メソッド】
    指定された文字で区切り、分割して、リストに格納した結果を返す
    '''
    def split(self, obj):
        sRslt = list()
        idx = 0

        for n in range(len(self.st)):
            if self.ch[n] != obj:
                if len(sRslt)<=idx:
                    sRslt.append(self.ch[n])
                else:
                    sRslt[idx]=sRslt[idx]+self.ch[n]
            else:
                idx += 1

        return sRslt


    '''
    【文字全検索メソッド】
    指定された文字パターンを検索し、全ての一致する文字パターンの開始位置をリストに格納した結果を返す
    '''
    def search(self, pattern):
        rslt = bm_search(self.st, pattern)
        return rslt

    '''
    【文字置換メソッド】
    指定された文字パターンを指定された文字パターンに置換した結果を返す
    但し、指定された文字パターンが重なっている場合は、最初の文字パターンのみ変換される
    '''
    def replace(self, oldPattern, newPattern):

        #置換対象候補の文字パターン開始位置をリストに格納
        repList = bm_search(self.st, oldPattern)

        newPatList = list(newPattern)
        adjustBase = len(newPattern)-len(oldPattern)    #置換時の基礎となるインデックス調整値
        adjust = adjustBase                             #置換時のインデックス調整値
#         print(repList)

        if repList != None:
            for n in range(len(repList)):
                if len(repList)>1 and n<len(repList)-1:   #置換候補が２つ以上あり、最後の置換でない場合
#                     print(f"{n}の要素：{repList[n]}，{n+1}の要素：{repList[n+1]}")

                    #最初の文字置換
                    if n==0:
                        #文字パターンを置換
#                         print(f"前：self.ch:{self.ch}")
                        self.ch [repList[n]:repList[n]+len(oldPattern)]=newPatList
#                         print(f"後：self.ch:{self.ch}")
                    #最後の文字置換
                    elif n==len(repList):
                        #文字パターンを置換
                        self.ch [repList[n]+adjust:repList[n]+len(oldPattern)+adjust]=newPatList
                    #最初と最後を除く文字置換
                    else:
                        #前後の置換候補文字パターンの重複判定
#                         print(f"repList[n]-repList[n-1]={repList[n]-repList[n-1]}")
#                         print(f"len(oldPattern):{len(oldPattern)}")

                        if repList[n]-repList[n-1] >= len(oldPattern):
#                             print("true route")

                            #文字パターンを置換
#                             print(f"repList[n]+adjust:{repList[n]+adjust}")
#                             print(f"repList[n]+len(oldPattern)+adjust:{repList[n]+len(oldPattern)+adjust}")
#                             print(f"前：self.ch:{self.ch}")
                            self.ch [repList[n]+adjust:repList[n]+len(oldPattern)+adjust]=newPatList
                            adjust = adjust + adjustBase

#                             print(f"後：self.ch:{self.ch}")
#                             print(f"adjust:{adjust}")

#                     print(f"XXXn:{n}")

                else:
                    if len(repList)==1: #置換候補が１つの時
                        #文字パターンを置換
#                         print(f"前：self.ch:{self.ch}")
                        self.ch [repList[n]:repList[n]+len(oldPattern)]=newPatList
#                         print(f"後：self.ch:{self.ch}")

                    else: #最後の文字置換処理
                    #文字パターンを置換
#                         print(f"前：self.ch:{self.ch}")
                        self.ch [repList[n]+adjust:repList[n]+len(oldPattern)+adjust]=newPatList
#                         print(f"後：self.ch:{self.ch}")

            self.str = ""

            #文字配列を自身の文字列に変換
            for n in range(len(self.ch)):
                self.str = self.str+self.ch[n]

            #変換した文字列を自身のものとして格納
            self.__init__(self.str)

        return self.str