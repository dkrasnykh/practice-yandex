package training_1_0.hw7.advertising;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

class Position implements Comparable {
    int x;
    int type;
    int other;
    //int open;
    Set<Position> open;

    public Position(int x, int type, int other) {
        this.x = x;
        this.type = type;
        this.other = other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && type == position.type && other == position.other;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, type, other);
    }

    @Override
    public int compareTo(Object o) {
        Position other = (Position) o;
        return (this.x == other.x) ? this.type - other.type : this.x - other.x;
    }
}

public class Advertising {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int start = Integer.MAX_VALUE;
            int end = 0;
            int s;
            int e;
            String line;
            int space;
            List<Position> events = new ArrayList<>();

            int[] starts = new int[n];
            int[] ends = new int[n];

            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                space = line.indexOf(' ');
                s = Integer.parseInt(line.substring(0, space));
                e = Integer.parseInt(line.substring(space + 1));
                if (s < start) {
                    start = s;
                }
                if (e > end) {
                    end = e;
                }
                starts[i] = s;
                ends[i] = e;
                //events.add(new Position(s, e, ))
                /*
                -2 - начало отрезка
                 2 - конец отрезка

                -1 - начало потенциальной точки
                 1 - конец потенциальной точки
                */
                if (e - s >= 5) {
                    events.add(new Position(s, -2, e));
                    events.add(new Position(e, 2, s));
                }

            }

            //добавить потенциальные точки
            int x1 = start;
            int x2 = start + 5;
            while (x2 <= end) {
                events.add(new Position(x1, -1, x2));
                events.add(new Position(x2, 1, x1));
                /*
                x1 = x2;
                x2 = x1 + 5;
                */
                x1++;
                x2 = x1 + 5;
            }

            Collections.sort(events);
            Set<Position> open = new HashSet<>();
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).type == -2) {
                    open.add(events.get(i));

                } else if (events.get(i).type == 2) {
                    open.remove(new Position(events.get(i).other, -2, events.get(i).x));
                } else if (events.get(i).type == 1) {
                    //закрываем потенциальную точку
                    //в множестве open нужно найти список подходящих элементов
                    int d = events.get(i).x - 5;
                    //long max = open.stream().filter(e1 -> (e1.x <= d)).count();
                    events.get(i).open = new HashSet<>();
                    events.get(i).open = open.stream().filter(e1 -> (e1.x <= d)).collect(Collectors.toSet());
                    //events.get(i).open = (int) max;
                }


            }

            /*
            1) Выбрать первый (самый ранний) потенциальный отрезок из списка. Удалить из множества все точки, которые с ним пересекаются
            2)
            */

            //Position p = events.stream().filter(p1 -> p1.type == 1).max((p1, p2) -> (p1.open.size()>0 && p1.open.size() == p2.open.size()) ? p2.x - p1.x : p2.open.size() - p1.open.size()).get();

            Optional<Position> popt = events.stream().filter(p1 -> p1.type == 1).max((p1, p2) -> p1.open.size() - p2.open.size());
            if (!popt.isPresent()) {
                writer.println("0 1 6");
                return;
            }
            //Position p = events.stream().filter(p1 -> p1.type == 1).max((p1, p2) -> p1.open.size() - p2.open.size()).get();
            Position p = popt.get();

            //if(p.){}

            events.clear();
            for (int i = 0; i < n; i++) {
                Position pos = new Position(starts[i], -2, ends[i]);
                if (!p.open.contains(pos)) {
                    //проверяем отрезки
                    if (starts[i] < p.other && ends[i] >= p.other && ends[i] <= p.x) {
                        events.add(new Position(starts[i], -2, p.other));
                        events.add(new Position(p.other, 2, starts[i]));
                    } else if (starts[i] >= p.other && starts[i] <= p.x && ends[i] >= p.other && ends[i] <= p.x) {

                    } else if (ends[i] > p.x && starts[i] >= p.other && starts[i] <= p.x) {
                        events.add(new Position(p.other, -2, ends[i]));
                        events.add(new Position(ends[i], 2, p.other));
                    } else {
                        events.add(new Position(starts[i], -2, ends[i]));
                        events.add(new Position(ends[i], 2, starts[i]));
                    }
                }

            }

            /*
            int x1 = start;
            int x2 = start + 5;
            while (x2 <= end) {
                events.add(new Position(x1, -1, x2));
                events.add(new Position(x2, 1, x1));
                x1 = x2;
                x2 = x1 + 5;
            }
            */
            x1 = start;
            x2 = start + 5;
            while (x2 < p.other) {
                events.add(new Position(x1, -1, x2));
                events.add(new Position(x2, 1, x1));
                /*
                x1 = x2;
                x2 = x1 + 5;
                */
                x1++;
                x2 = x1 + 5;
            }

            x1 = p.x;
            x2 = x1 + 5;
            while (x2 < end) {
                events.add(new Position(x1, -1, x2));
                events.add(new Position(x2, 1, x1));
                /*
                x1 = x2;
                x2 = x1 + 5;
                */
                x1++;
                x2 = x1 + 5;
            }
            Collections.sort(events);
            open.clear();
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).type == -2) {
                    open.add(events.get(i));

                } else if (events.get(i).type == 2) {
                    open.remove(new Position(events.get(i).other, -2, events.get(i).x));
                } else if (events.get(i).type == 1) {
                    //закрываем потенциальную точку
                    //в множестве open нужно найти список подходящих элементов
                    int d = events.get(i).x - 5;
                    //long max = open.stream().filter(e1 -> (e1.x <= d)).count();
                    events.get(i).open = new HashSet<>();
                    events.get(i).open = open.stream().filter(e1 -> (e1.x <= d)).collect(Collectors.toSet());
                    //events.get(i).open = (int) max;
                }
            }

            //Position pnext = events.stream().filter(p1 -> p1.type == 1).max((p1, p2) -> (p1.open.size() == p2.open.size()) ? p2.x - p1.x : p2.open.size() - p1.open.size()).get();
            Optional<Position> poptnext = events.stream().filter(p1 -> p1.type == 1).max((p1, p2) -> p1.open.size() - p2.open.size());
            if (!poptnext.isPresent()) {
                writer.println(p.open.size() + " " + p.other + " " + (p.other + 10));
                return;
            }
            Position pnext = poptnext.get();
            writer.println(p.open.size() + pnext.open.size() + " " + p.other + " " + pnext.other);
            /*
            что если составить массив x на котором будут отображены количество точек
             */
            int test = 5;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}