

def get_mid(i, j):
    return i + int((j - i) / 2)


def search_greater_char(curr_char, input_str):
    i = 0;
    j = len(input_str) - 1
    while i <= j:
        mid = get_mid(i, j)
        if input_str[mid - 1] == curr_char and ord(input_str[mid]) > ord(curr_char):
            return mid
        if ord(input_str[mid]) <= ord(curr_char):
            i = mid + 1
        else:
            j = mid - 1
    return -1


def rearrange(input_str):
    i = 1
    size = len(input_str)

    while i < size:
        if input_str[i] == input_str[i - 1]:
            next_char_index = search_greater_char(input_str[i], input_str)
            if next_char_index == -1:
                return False
            input_str[i], input_str[next_char_index] = input_str[next_char_index], input_str[i]
        i = i + 1
    return True


def no_repeating_adjacency(assorted_str):
    input_str = list(assorted_str)
    is_rearranged = rearrange(input_str)
    if is_rearranged:
        return input_str
    else:
        return "Did not work"


if __name__ == '__main__':
    # Assuming string is already sorted lexicographically
    print(no_repeating_adjacency('aaabc'))
    print(no_repeating_adjacency('aaabb'))
    print(no_repeating_adjacency('aa'))
    print(no_repeating_adjacency('aaaabc'))
