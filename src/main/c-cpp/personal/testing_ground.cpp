#include <iostream>
#include <map>

using namespace std;

void map_example();
void multimap_example();

int main(int argc, char const *argv[])
{
    int i = INT_MAX - 1;
    cout << i + 2 << endl;
    cout << LONG_MAX << ' ' << std::numeric_limits<int>::max() << endl;
    // 9,223,372,036,854,775,807

    map_example();
    multimap_example();

    return 0;
}

void map_example() {
    map<int, string> m;
    m[2] = "string2";
    m[1] = "string1";
    m[3] = "string3";

    map<int, string>::iterator it; // auto it;
    for (it = m.begin(); it != m.end(); ++it)
    {
        cout << it->first << " => " << it->second << '\n';
    }
    
    string value = m.begin()->second;
    cout << "Erase " << value << endl;

    m.erase(m.begin());
    for (it = m.begin(); it != m.end(); ++it)
    {
        cout << it->first << " => " << it->second << '\n';
    }
}

void multimap_example() {
    std::multimap<int, std::string> myMultimap;

    // Add key-value pairs to the multimap
    myMultimap.emplace(1, "Apple");
    myMultimap.emplace(2, "Banana");
    myMultimap.emplace(1, "Apricot");
    myMultimap.emplace(3, "Cherry");

    // Loop to get and remove each element
    while (!myMultimap.empty()) {
        // Get the first element using begin()
        std::multimap<int, std::string>::iterator it = myMultimap.begin();

        // Print the element's key and value
        std::cout << "Key: " << it->first << ", Value: " << it->second << std::endl;

        // Remove the element using erase()
        myMultimap.erase(it);
    }
}
