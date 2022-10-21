package ku.cs.services;
public interface DataSource<T> {
    T readData();
    void writeData(T t); // มันจะมองเป็น public เสมอ สามารถละได้เลย
// method จะมองเป็น ค่าคงที่ จะมองว่า reference เรียกตัวเเปรไหนได้บ้าง
//ไม่ได้สร้าง obj มี method ได้ เป็นเเบบ signature
// เทาๆๆ คือยังๆม่ได้ถูกเอาไปใช้
// เป็น abstract method จบด้วย ;
// ใส่ T เป็นตัวเเปร เรียกว่า generic type มองเป็นตัวเเปรของ data type ที่จะเขียน data
}
