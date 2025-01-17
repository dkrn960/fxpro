package adminBuy;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import myMain.Opener;




public class MainHandler implements Initializable {
	
	//"테이블 정보보기 " 버튼 입니다.
    @FXML
    private Button bt_view;

    //테이블의 테이터를 선택 했을때 각각의 테이터를 출력할 곳입니다.

    /*테이블 뷰는 리스트 뷰와 다르게 데이터가 많아서 배열로 하는것 보다 
    	클래스를 만들어서 하는 것이 쉽고 간편합니다.
    	그래서  TableVDate 라는 클래스를 만들어서 데이트 타입을 클래스로 
    	지정 했습니다.
    	문자열배열을 이용해도 됩니다.
    	그렇게 하면 실제 코딩에서 자료를 받아 출력할때 많이 귀찮아 집니다.
   */
    @FXML
    private TableView<TableData> tableView1;
    
    //테이블 칼럼을 칼럼갯수(출력할 데이터의 칸수) 만큼 선언. 
    @FXML
    private TableColumn tableViewColumn1,tableViewColumn2;
    @FXML
    private TableColumn tableViewColumn3,tableViewColumn4;
    
    private selectTotalDAO selectTotal;
    
    private Opener opener;
    
    
    public void setOpener(Opener opener) {
		this.opener = opener;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	selectTotal = new selectTotalDAO();
    	/*시작 하면서 테이블의 테이터를 보시길 원하시면
    	 * 아래의  sel_bt_view의 부분을 여기 부분에 복붙 하시면 됩니다.
    	 * 따로 핸들러를 클릭해야 테이블의 데이터를 보이게 할려고
    	 * 밑에 핸들러에 칼럼부분을 작성 했습니다.
    	 * 칼럼설정 부분은 어느쪽에 있어도 상관없으면 이곳이나 아래쪽에 
    	 * 있기만 하면 됩니다. 
    	 */
    	sel_bt_view();
    }
    
    //회색 버튼 의 핸들러 부분 여기를 누르면 테이블뷰의 리스트가 출력됩니다.
    @FXML
    void sel_bt_view() {
    	
    	//table뷰의 컬럼부분   컬럼은 5개이다.
    	//항목이 추가 되면 숫자만 증가시켜서 추가 하시면 되고
    	//TableData 클래스에 여기하고 맞게 항목을 같이 
    	//추가 하면 됩니다.
		TableColumn tableViewColumn1=tableView1.getColumns().get(0);
		tableViewColumn1.setCellValueFactory(new PropertyValueFactory("strNum"));
		
		TableColumn tableViewColumn2=tableView1.getColumns().get(1);//테이블븅의 첫번째 컬럼을 생성한다.
		tableViewColumn2.setCellValueFactory(new PropertyValueFactory("strId"));//tableㅇ의 첫번째 컬럼을 strid와대응시킨다.
		
		
		TableColumn tableViewColumn3=tableView1.getColumns().get(2);
		tableViewColumn3.setCellValueFactory(new PropertyValueFactory("strName"));
		
		TableColumn tableViewColumn4=tableView1.getColumns().get(3);
		tableViewColumn4.setCellValueFactory(new PropertyValueFactory("strGroup"));

		
		
		
		//for문 안에서 입출력에 사용할 직접만든 테이블 클래스를 선언 합니다. 
		TableData vtData = null;
		/*출력할 데이터의 갯수를 임의로 정했습니다.
		 * 실전에서는 보통 배열로 받습니다.
		 * 그래서 nCnt 에 배열의 갯수를 넣으면 되겠습니다.
		 * 예> int nCnt = 배열명.length;
		 * 이렇게 하면 배열의 갯수만큼 처리 해서 출력해 줍니다.
		 * 지금 예제에서는 1000 이라 1000줄의 데이터가 생깁니다.
		 */
		 ArrayList<selectTotalDTO> totals = selectTotal.selectAll();
    	for (selectTotalDTO total : totals) { // 테이블들어갈 내용을 만드는 부분
			
			//컴럼의 갯수만큼 문자열 배열을 만들어 줍니다.
			String[] strRecord = new String[4]; // 컬럼수가5개이다.
						
			strRecord[0] = Integer.toString(total.getNum());
			strRecord[1] = total.getEntrydate();
			strRecord[2] = total.getBuyby();
			strRecord[3] = total.getMemberId();		

			// TableData클래스의  데이터를 한번에 입력
			//하면 생성자를 만들어 그것을 출력에 사용한다.
			vtData = new TableData(strRecord);
			tableView1.getItems().add(vtData); 
		}
    }
    
    
    public void accessBtn() {
    	opener.accessOpen();
    }
    
    public void blackBtn() {
    	opener.blackOpen();
    }
}