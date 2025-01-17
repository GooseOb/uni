#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    char* marka;
    unsigned int rocznik;
    char* kolor;
    int cena;
    unsigned int przebieg;
    char* przedmioty[];
} Auto;

void eksportuj(Auto* a);
Auto* imporuj(char* path, unsigned int* len);
float srednia(char* path);

int main(int argc, char* argv[]) {
    Auto a;
    if (argc > 3 && strcmp(argv[1], "--cena ")) {
        unsigned int len;
        Auto* arr = imporuj(argv[2], &len);
        FILE* f = fopen(argv[3], "w");
        for (int i=0; i < len; ++i) {
            printf("%d\n", arr[i].cena);
            fprintf(f, "%d\n", arr[i].cena);
        }
        fclose(f);
        printf("s: %f\n", srednia(argv[3]));
    }
    return 0;
    Auto okaz = {
        .marka = "Toyota Corolla",
        .kolor = "czerwony",
        .cena = 15000
    };

    printf("Podaj rocznik: ");
    scanf("%u", &okaz.rocznik);
    printf("Podaj przebieg: ");
    scanf("%u", &okaz.przebieg);

    Auto okaz2;
    memcpy(&okaz2, &okaz, sizeof(Auto));
    okaz2.kolor = "zielony";
    okaz2.cena = 12500;

    eksportuj(&okaz);
    eksportuj(&okaz2);

    return 0;
}

void eksportuj(Auto* a) {
    char mode[] = "w";
    char userMode;
    char path[100];
    printf("Podaj tryb eksportu (w/a): ");
    scanf(" %c", &userMode);
    if (userMode == 'a') mode[0] = 'a';
    printf("Podaj sciezke: ");
    scanf("%s", path);
    FILE* f = fopen(path, mode);
    if (f == NULL) {
        printf("Nie można otworzyć pliku.\n");
        return;
    }
    fprintf(f, "%s;%u;%s;%d;%u\n", a->marka, a->rocznik, a->kolor, a->cena, a->przebieg);
    fclose(f);
}

Auto* imporuj(char* path, unsigned int* len) {
    FILE* f = fopen(path, "r");
    if (f == NULL) {
        printf("Nie można otworzyć pliku.\n");
        return NULL;
    }

    (*len) = 0;
    char ch;
    while ((ch = fgetc(f)) != EOF)
        if(ch == '\n') ++(*len);
    rewind(f);

    Auto* arr = malloc(sizeof(Auto)*(*len));
    if (arr == NULL) {
        printf("Błąd alokacji pamięci.\n");
        fclose(f);
        return NULL;
    }

    for (int i=0; i < *len; ++i) {
        arr[i].marka = malloc(100);
        arr[i].kolor = malloc(100);
        if (fscanf(f, "%99[^;];%u;%99[^;];%d;%u\n",
            arr[i].marka,
            &(arr[i].rocznik),
            arr[i].kolor,
            &(arr[i].cena),
            &(arr[i].przebieg)
        ) != 5) {
            printf("Blad odczytu danych z pliku.\n");
            fclose(f);
            for (int j = 0; j < i; ++j) {
                free(arr[j].marka);
                free(arr[j].kolor);
            }
            free(arr);
            return NULL;
        };
    }
    fclose(f);

    return arr;
}

float srednia(char* path) {
    FILE* f = fopen(path, "r");
    if (f == NULL) {
        printf("Nie można otworzyć pliku.\n");
        return 0;
    }
    unsigned int i = 0;
    float sum = 0;
    float num;
    while (!feof(f)) {
        fscanf(f, "%f\n", &num);
        sum += num;
        ++i;
    }
    fclose(f);
    return sum/i;
}