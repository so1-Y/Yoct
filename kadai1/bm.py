'''
Created on 2022/06/04

@author: so1ya
'''

def max(a, b):
    return a if a > b else b

def bm_search(string, pattern):
    pat_len = len(pattern)
    skip = [pat_len for _ in range(256)]

    idxList = list()
    idxLen = 0

    for i in range(pat_len):
        skip[ord(pattern[i])] = pat_len - i - 1

    i = pat_len - 1


    while i < len(string):
        j = pat_len - 1
        isNotConti = True

        while string[i] == pattern[j] and isNotConti:
            if j == 0:
                idxList.append(i)
                idxLen += 1

            if len(idxList)>1 and idxList[idxLen-2] == i-pat_len:      #直前に同文字パターンが連続している場合
                isNotConti = False
            else:
                i -= 1
                j -= 1

        if ord(string[i])<len(skip):
            i = i + max(skip[ord(string[i])], pat_len-j)
        else:
            i = len(string)+1

    if len(idxList)< 1 :
        return None
    else:
        return idxList

# string = "jiabcaakjbiabcabcaoiijabcabcekjljkabca"
# strink = "01234567890123456789012345678901234567"
# pattern= "abca"
#
# rslt = bm_search(string,pattern)
# print(rslt)
