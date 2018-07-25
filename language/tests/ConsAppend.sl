function null() {}

function append(list1, list2) {
    aux = null();

    while (list1 != null()) {
        aux = cons2(head(list1), aux);
        list1 = tail(list1);
    }

    while (aux != null()) {
        list2 = cons2(head(aux), list2);
        aux = tail(aux);
    }

    return list2;
}

function main() {
    list1 = cons2(1, cons2(2, cons2(3, null())));
    list2 = cons2(4, cons2(5, cons2(6, null())));

    fullList = append(list1, list2);
    println(fullList);
}
