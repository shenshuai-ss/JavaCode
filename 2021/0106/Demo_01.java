public class Demo_01 {
        public static int CyclicGqam(int n, int key) {
            boolean[] array = new boolean[n];
            for (int i=0; i<n; i++) {
                array[i]=true;
            }
            int count = 1;
            int index = 0;
            int peopleLeft = n;
            while (peopleLeft >= 2) {
                if (array[index] == true && count == key) {
                    array[index] = false;
                    peopleLeft --;
                    count = 1;

                } else if (array[index] && count != key) {
                    count ++;
                    index = (++ index) % n;
                }
                else {
                    index = (++ index) % n; //[0, n-1]
                }


            }
            for (int i=0; i<n; ++i) {
                if(array[i]) {
                    return i;
                }
            }
            return -1;
        }
        public static void main(String[] args) {

            System.out.println(CyclicGqam(4, 3));
        }
}

