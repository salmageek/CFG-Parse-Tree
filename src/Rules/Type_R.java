package Rules;


public class Type_R implements Node {
   DataType_R dateType;
   Array_R array_;

    public Type_R(DataType_R dateType, Array_R array_) {
        this.dateType = dateType;
        this.array_ = array_;
    }

    @Override
    public void printNode() {
        System.out.print("Type:- ");
        if (dateType != null)
            System.out.print("<DataType> ");
        if (array_ != null)
            System.out.print("<Array> ");
        System.out.println();

        if (this.dateType == null)
            System.out.println("DataType: null");
        else
            dateType.printNode();

        if (this.array_ == null)
            System.out.println("Array: null");
        else
            array_.printNode();
    }
}
