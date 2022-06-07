'''
Created on 2022/06/03

@author: so1ya
'''
from kadai1.String import String

s = String("aiue")
l = String(["a","b","c"])
i = String(10)
il = String([1,2,3,4,5])
f = String(True)

print("【表示】")
s.show()
l.show()
i.show()
il.show()
f.show()


print("\n【文字の長さ】")
print(s.length())

print("\n【文字列を１文字ずつ分割してリストに格納した結果を表示】")
print(s.splitEach())


print("\n【文字列を指定された文字で分割してリストに格納した結果を表示】")
st = String("abd,ege,aaa,d,te,a")
print(st.split(","))


print("\n【文字列から指定された文字の先頭位置を検索しリストに保存】")
s = String("jiabcabcaakjdasfabcaabcagabciddabcax")
print(s.search("abca"))

print("\n【文字列から指定された文字を新たに指定された文字に置換】")
s.replace("abca", "■")
s.show()

print("\n【置換前の文字は無いことを確認】")
print(s.search("abc"))
