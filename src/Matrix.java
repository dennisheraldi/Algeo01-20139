import java.util.*;

import javax.lang.model.util.ElementScanner14;

import java.io.*;

public class Matrix {

	// Atribut
	public double[][] Mat;
	protected int brs, kol;


	// Method:

	/* ===CONSTRUCTOR=== */
	public Matrix(int i, int j) {
		this.brs = i;
		this.kol = j;
		
		Mat = new double[i][j];
		for (i = 0; i < this.brs; i++) {
			for (j = 0; j < this.kol; j++) {
				Mat[i][j] = 0;
			}
		}
	}

	/* ===SELEKTOR=== */
	void getBaris(int i) {
		// Mengeluarkan baris Matrix M dengan indeks ke-i
		int j;

		for (j = 0; j < this.kol; j++) {
			System.out.print(this.Mat[i][j] + " ");
		}
	}

	void getKolom(int j) {
		// Mengeluarkan kolom Matrix M dengan indeks ke-j
		int i;

		for (i = 0; i < this.brs; j++) {
			System.out.println(this.Mat[i][j]);
		}
	}

	public int getJmlBrs(){
		// Mengeluarkan jumlah baris
		return this.brs;
	}

	public int getJmlKol(){
		//Mengeluarkan jumlah kolom
		return this.kol;
	}

	int getFirstIndeks(int jmlBrs){
		/* return indeks kolom tidak nol pertama*/
		boolean found = false;
		int i = 1;

		while((i <= this.kol) && !found){
			if (this.Mat[jmlBrs][i] != 0){
				found = true;
			}else{
				i++;
			}
		}
		if (found){
			return i;
		}else{
			return this.kol;
		}
	}


	void setElmt(int baris, int kolom, double var){
		this.Mat[baris][kolom] = var;
	}


	// =========================================================================================================//

	int notZeroBrs(){
		/* return indeks baris tidak nol pertama pada indeks kolom ke-0*/
		int i = 0;

		while((i < this.brs) && this.Mat[i][0] == 0){
			i++;
		}
		return i;
	}

	/* ===INPUT ATAU OUTPUT === */
	void bacaMatriks() {
		// Mengisi elemen matriks M[i,j] dengan input pengguna
		Scanner in = new Scanner(System.in);
		int i, j;
		for (i = 0; i < this.brs; i++) {
			for (j = 0; j < this.kol; j++) {
				this.Mat[i][j] = in.nextDouble();
			}
		}
	}
	public void bacaFileMatriks (String FileName) {
		// Membaca file eksternal kemudian dikonversi kedalam tipe Matrix
		try{
			String dir = "../test/" + FileName;
			File file = new File(dir);
			int i,j;
			int Nbrs = 0 , Nkol = 0;
			Scanner input = new Scanner(file);

			// Menghitung jumlah baris matriks
			while(input.hasNextLine()) {
				Nbrs++;
				input.nextLine();
			}
			input.close();

			input = new Scanner(file);
			Scanner num = new Scanner(input.nextLine());

			// Menghitung jumlah kolom matriks
			while (num.hasNextDouble()){
				Nkol++;
				num.nextDouble();
			}
			num.close();
			input.close();

			input = new Scanner(file);

			this.brs = Nbrs;
			this.kol = Nkol;
			// Re-assign ukuran matriks
			Mat = new double[Nbrs][Nkol];

			// Pengisian nilai yang terbaca pada file ke dalam Matrix
			for (i=0; i<Nbrs;i++){
				for (j=0; j < Nkol; j++){
					this.Mat[i][j] = input.nextDouble();
				}
			}
			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("File tidak ditemukan. Periksa kembali nama file.");
		}
	}	
	void tulisMatriks() {
		// Mencetak elemen matriks berukuran M x N ke layar
		int i, j;

		for (i = 0; i < this.brs; i++) {
			for (j = 0; j < this.kol; j++) {
				System.out.print(this.Mat[i][j] + " ");
			}
			System.out.println();
		}
	}

	
	
	
	/* ===FUNGSI HELPER=== */
	
	
	
	public void tukarBaris(int M, int N) {
		// Menukar baris ke-M dengan baris ke-N
		int j; 
		double temp;

		if (M < 0 || M > this.brs){
			System.out.println("Masukkan baris 1 tidak valid");
		}else if (N < 0 || N > this.brs){
			System.out.println("Masukkan baris 2 tidak valid");

		}else{
			for (j = 0; j < this.kol; j++) {
				temp = this.Mat[M][j];
				this.Mat[M][j] = this.Mat[N][j];
				this.Mat[N][j] = temp;
			}
		}	
	}

	public void tambahBaris(int M, int N, double k) {
		// Menambah baris ke-M dengan baris ke-N
		int j;

		if (M < 0 || M > this.brs){
			System.out.println("Masukkan baris 1 tidak valid");
		}else if(N < 0 || N > this.brs){
			System.out.println("Masukkan baris 2 tidak valid");
		}else{
			for (j = 0; j < this.kol; j++) {
				this.Mat[M][j] += (this.Mat[N][j] * k);
			}
		}
	}

	void kurangBaris(int M, int N) {
		// Mengurang baris ke-M dengan baris ke-N
		int j;
		for (j = 0; j < this.kol; j++) {
			this.Mat[M][j] = this.Mat[M][j] - this.Mat[N][j];
		}
	}

	void kaliBaris(int i, double val) {
		// Mengalikan baris ke-i dengan val
		if (i < 0 || i > this.brs){
			System.out.println("Masukkan baris tidak valid");
		}else{
			if (val == 0){
				System.out.println("Masukkan konstanta tidak valid");
			}else{
				for (int j = 0; j < this.kol; j++) {
					this.Mat[i][j] = this.Mat[i][j] * val;
				}

			}

			
		}
	}

	double jumlahKaliKolom(int i, int j) {
		int m;
		double hasil = 0;
	
		for (m = 0; m < this.brs; m++) {
			hasil += this.Mat[m][i] * this.Mat[m][j];
		}
		return hasil;
	}

	double jumlahKolom(int j) {
		int i;
		double hasil = 0;

		for (i = 0; i < this.brs; i++) {
			hasil += this.Mat[i][j];
		}
		return hasil;
	}
	void Transpose() {
		// Menghasilkan matriks transpose
		int i, j;
		double[][] M;
		M = new double[this.brs][this.kol];

		for (i = 0; i < this.brs; i++) {
			for (j = 0; j < this.kol; j++) {
				M[j][i] = this.Mat[i][j];
			}
		}

		for (i = 0; i < this.brs; i++) {
			for (j = 0; j < this.kol; j++) {
				this.Mat[i][j] = M[i][j];
			}
		}
	}

	void TambahElmt(Matrix mat, int brs1, int brs2, double val){
		for (int i = 0; i < mat.kol; i++){
			double tmp = val*mat.Mat[brs1][i];
			mat.setElmt(brs2, i, mat.Mat[brs2][i]+tmp);
		}
	}

	void reduceMatriks(double M[][], int i, int j) {
		// Membuat matriks tanpa memasukkan baris ke-i dan kolom ke-j
		int u, v;
		int m, n;
		double T[][];
		T = new double[this.brs][this.kol];

		u = 0;
		m = 0;
		while (m < this.brs) {
			if (u == i) {
				m += 1;
			}
			if (m != this.brs) { 
				for (v = 0; v < this.kol; v++) {
					T[u][v] = this.Mat[m][v];
				}
			}
			u += 1;
			m += 1;
		}

		v = 0;
		n = 0;
		while (n < this.kol) {
			if (v == j) {
				n += 1;
			} 
			if (n != this.kol) {
				for (u = 0; u < this.brs; u++) {
					T[u][v] = T[u][n];
				}
			}
			v += 1;
			n += 1;
		}
		
		for (i = 0; i < this.brs-1; i++) {
			for (j = 0; j < this.kol-1; j++) {
				M[i][j] = T[i][j];
			}
		}
	}

	void copyMatriks(double M[][]) {
		int i,j;

		for (i = 0; i < this.brs; i++) {
			for (j = 0; j < this.kol; j++) {
				this.Mat[i][j] = M[i][j];
			}
		}
	}

	

	/* VALIDITAS */
	boolean isZero(int i, int j) {
		// Menghasilkan true apabila baris ke-i kolom ke-j dari matriks M bernilai 0
		return (this.Mat[i][j] == 0);
	}

	boolean isBarisZero(int i) {
		// Menghasilkan true apabila nilai baris ke-i dari matriks M bernilai 0
		boolean flag = true;
		int j = 0;

		while (j < this.kol && flag) {
			if (this.Mat[i][j] != 0) {
				flag = false;
			}
		}
		return flag;
	}

	boolean isPersegi() {
		// Menghasilkan true apabila baris matriks = kolom matriks
		return (this.brs == this.kol);
	}

	boolean isIdxValid(int i, int j) {
		// Menghasilkan true apabila index valid
		return (i >= 0 && i < this.brs && j >= 0 && j < this.kol);
	}

	/*
	 * ===FUNGSI - FUNGSI=== 
	 * - determinan 
	 * - inversMatrix 
	 * - gaussEliminasi 
	 * - gaussJordanEliminasi
	 * 
	 * 
	 * 
	 * 
	 * 
	 */



	double determinanReduksiBrs() {
		int i, j;
		double val;
		double det = 1;
		double[][] M = new double[this.brs-1][this.kol-1];
		Matrix mr = new Matrix(this.brs-1,this.kol-1);
	
		if (this.brs == 2 && this.kol == 2) {
			if (this.notZeroBrs() == this.brs) {
				det = 0;
			} else if (this.notZeroBrs() == 1) {
				this.tukarBaris(0,1);
				det = -(this.Mat[0][0] * this.Mat[1][1]);
			} else {
				if (this.Mat[1][0] != 0) {
					val = this.Mat[1][0]/this.Mat[0][0];
					this.kaliBaris(0,val);
					this.kurangBaris(1,0);
					this.kaliBaris(0,1/val);
					det = this.Mat[0][0] * this.Mat[1][1];
				} else {
					det = this.Mat[0][0] * this.Mat[1][1];
				}
			}
		} else {
			if (this.notZeroBrs() == this.brs) {
				det = 0;
			} else if (this.notZeroBrs() != 0 && this.notZeroBrs() != this.brs) {
				this.tukarBaris(0,this.notZeroBrs());
				for (i = 1; i < this.brs; i++) {
					if (this.Mat[i][0] != 0) {
						val = this.Mat[i][0]/this.Mat[0][0];
						this.kaliBaris(0,val);
						this.kurangBaris(i,0);
						this.kaliBaris(0,1/val);
					}
					this.reduceMatriks(M,0,0);
					mr.copyMatriks(M);
				}
				det = -(this.Mat[0][0]) * mr.determinanReduksiBrs();
			} else {
				for (i = 1; i < this.brs; i++) {
					if (this.Mat[i][0] != 0) {
						val = this.Mat[i][0]/this.Mat[0][0];
						this.kaliBaris(0,val);
						this.kurangBaris(i,0);
						this.kaliBaris(0,1/val);
					}
					this.reduceMatriks(M,0,0);
					mr.copyMatriks(M);
				}
				det = this.Mat[0][0] * mr.determinanReduksiBrs();
			}
		}
		return det;
	}

	public double determinanKofaktor() {
		double det=0;
		int i,j,k,x,y;
		Matrix mr = new Matrix((this.brs-1),(this.kol-1));

		if (this.brs==2 && this.kol==2) { // Basis
			
			det = (this.Mat[0][0]*this.Mat[1][1]) - (this.Mat[0][1]*this.Mat[1][0]);
			return det;
		} else { // Rekurens
			for (i = 0; i < this.kol ; i++) {
				if (this.Mat[0][i] != 0) {
					y = 0;
					for (j = 0; j < this.brs ; j++) {
						x = 0;
						for (k = 0; k < this.kol ; k++) {
							if (k != i) { // Membentuk kofaktor matriks
							mr.Mat[y][x] = this.Mat[j][k];
							x++;
							}
						}
						if (j != 0) {
							y++;
						}
					}
					if (i % 2 == 0) { // Menentukan tanda + dan - pada koefisien pengali kofaktor
					  det += this.Mat[0][i] * mr.determinanKofaktor();
					} else {
					  det -= this.Mat[0][i] * mr.determinanKofaktor();
					}
				}
			}
			return det;
		}	
	}
	
	void Kofaktor() {
		int i, j;
		double temp;
		double[][] M;
		M = new double[this.brs-1][this.kol-1];
		Matrix M1 = new Matrix(this.brs-1,this.kol-1);
		Matrix M2 = new Matrix(this.brs,this.kol);

		if (this.brs == 2 && this.kol == 2) {
			temp = this.Mat[0][0];
			this.Mat[0][0] = this.Mat[1][1];
			this.Mat[1][1] = temp;
			temp = this.Mat[0][1];
			this.Mat[0][1] = -(this.Mat[1][0]);
			this.Mat[1][0] = -(temp);
		} else {
			for (i = 0; i < this.brs; i++) {
				for (j = 0; j < this.kol; j++) {
					this.reduceMatriks(M,i,j);
					M1.copyMatriks(M);
					M2.Mat[i][j] = M1.determinanKofaktor();

					if((i+j)%2 != 0) {
						M2.Mat[i][j] = -(M2.Mat[i][j]);
					}
				}
			}
		
			for (i = 0; i < this.brs; i++) {
				for (j =0; j < this.kol; j++) {
					this.Mat[i][j] = M2.Mat[i][j];
				}
			}
		}
	}
	
	
	void Adjoin() {
		this.Kofaktor();
		this.Transpose();
	}
	
	
	
	void Invers() {
		int i, j;
		double[][] M;
		M = new double[this.brs][this.kol];
		double det = this.determinanKofaktor();

		if (det != 0) {
			this.Adjoin();
			for (i = 0; i < this.brs; i++) {
				for (j = 0; j < this.kol; j++) {
					M[i][j] = this.Mat[i][j]/det;
				}
			}
			
			this.copyMatriks(M);
		}
	}

	String metodeInvers(double M[][]) {
		// Menghasilkan perkalian matriks dengan matriks M
		int i, j, k;
		double hasil;
		String str = "";

		this.Invers();
		for (i = 0; i < this.brs; i++) {
			hasil = 0;
			for (k = 0; k < this.kol; k++) {
				hasil += this.Mat[i][k] * M[k][0];
			}
			str += "x" + (i+1) + "=" + (hasil) + " ";
		}
		return str;
	}

	String Cramer(double M[][]) {
		int i, j;
		double Mcopy[][];
		Mcopy = new double[this.brs][this.kol];
		double detAwal = this.determinanKofaktor();
		double detAkhir, hasil;
		String str = "";
		
		for (i = 0; i < this.brs; i++) {
			for (j = 0; j < this.kol; j++) {
				Mcopy[i][j] = this.Mat[i][j];
			}
		}
		
		if (detAwal != 0) {
			for (j = 0; j < this.kol; j++) {
				for(i = 0; i < this.brs; i++) {
					this.Mat[i][j] = M[i][0];
				}
				detAkhir = this.determinanKofaktor();
				hasil = detAkhir/detAwal;
				str += "x" + (j+1) + "=" + (hasil) + " ";
				for (i = 0; i < this.brs; i++) {
					this.Mat[i][j] = Mcopy[i][j];
				}
			}
		}
		return str;
	}
	
	
	void sortMatriks(){
		int i, j;
		
		
		if (this.brs > 1){
			for (i = 1; i < this.brs;i++){
				int brsMax = i;
				for (j = i + 1;j < this.brs;j++){
					int tempMaks = this.getFirstIndeks(j);
					if (tempMaks < this.getFirstIndeks(brsMax)){
						brsMax = j;
					}
				}
				this.tukarBaris(i, brsMax);
			}
		}

	}


	//fungsi untuk membuat matriks menjadi bentuk matriks echelon 
	void MakeEchelon(){

		int IdxFirst = 0;
		int k;

		double ElmtFirst;

		for (int i = 0; i < this.brs; i++){
			k = i;
			ElmtFirst = this.Mat[k][IdxFirst];

			while (ElmtFirst == 0){
				k++;
				if (k == this.brs){
					k = i;
					IdxFirst++;
					if (IdxFirst == this.kol){
						return;
					}
				}
				ElmtFirst = this.Mat[k][IdxFirst];
			}
			if (k != i){
				tukarBaris(k, i);
			}

			if (this.Mat[i][IdxFirst] != 0){
				this.kaliBaris(i, (1/(this.Mat[i][IdxFirst])));
			}

			for (int j = i + 1; j < this.brs; j++){
				double FirstElmt = this.Mat[j][i];
				double constant = (-1) * FirstElmt/this.Mat[i][IdxFirst];
				this.tambahBaris(j, i, constant); 
			}
			IdxFirst++;
		}
	}


	//fungsi untuk membuat matriks menjadi bentuk matriks echelon

	void MakeReduceEchelon(){
		int IdxFirst = 0;
		int k ;

		for (int i = 0; i < this.brs; i++){
			
			if (this.kol <= IdxFirst){
				return;
			}

			k = i;

			while (this.Mat[k][IdxFirst] == 0){
				k++;

				if (k == this.brs){
					k = i;
					IdxFirst++;

					if (IdxFirst == this.kol){
						return;
					}
				}
			}

			if (k != i){
				tukarBaris(k, i);
			}

			if (this.Mat[i][IdxFirst] != 0){
				this.kaliBaris(i, (1/this.Mat[i][IdxFirst]));
			}

			for (int j = 0; j < this.brs; j++){
				if (j != i){
					double FirstElmt = this.Mat[j][IdxFirst];
					double constant = (-1) * FirstElmt/this.Mat[i][IdxFirst];
					this.tambahBaris(j, i, constant);
				}
			}

			IdxFirst++;
		}
	}


	int jmlSolusi(){
		boolean isNol1 = true;
		boolean isNol2 = true;

		int i = 0;

		if ((this.Mat[this.brs-1][this.kol-1]) != 0.0d){
			isNol1 = false;
		}

		while ((i < this.kol-1) && isNol2){
			if ((this.Mat[this.brs-1][i]) != 0.0d){
				isNol2 = false;
			}
			i++;
		}

		int jmlSolusi; 
		if (isNol2 && isNol1){
			jmlSolusi = 2; //Memiliki solusi tak hingga
		}else if (isNol2 && !isNol1){
			jmlSolusi = 0; //Tidak Memiliki Solusi 
		}else{
			jmlSolusi = 1; //Memiliki Solusi Unik
		}

		return jmlSolusi;
	}




	// Fungsi untuk mengubah Matrix ke bentuk parametrik
	// Prekondisi JmlSolusi = 2 
	HashMap<String, String> MatrixToParam(){

		HashMap<String, String> SolusiParametrik = new HashMap<>();

		char VarBebas = 's'; //variabel bebas pertama
		int i, j;

		for (j = this.kol-2;j >= 0; j--){
			boolean AllZero = true;

			for (i = this.brs-1; i >= 0; i--){
				if (this.Mat[i][j] != 0){
					AllZero = false;
					break;
				}
			}

			if (AllZero || (this.Mat[i][j] != 1 )){
				SolusiParametrik.put("x" + (j+1), VarBebas + "");
				if (VarBebas == 'z'){
					VarBebas -= 25;
				}else{
					VarBebas++;
				}
			}
		}

		int JmlBrsNotZero = 0;
		i = 0;
		j = 0;
		boolean Zero = true;

		while(i < this.brs){
			Zero = true;
			while (Zero && j < this.kol){
				if (this.Mat[i][j] != 0){
					JmlBrsNotZero++;
					Zero = false;
				}
				j++;
			}
			i++;
		}
		
		for (i = 0; i < JmlBrsNotZero; i++){
			j = 0;

			while(this.Mat[i][j] != 1){
				j++;
			}
			
			SolusiParametrik.put("x" + (j+1), "");

			if(j != this.kol-2){
				for (int k = j+1; k < this.kol; k++){
					if(SolusiParametrik.get("x" + (j+1)) != null && SolusiParametrik.get("x" + (j+1)).equals("")){
						if (k != this.kol-1){
							if(this.Mat[i][k] > 0){
								SolusiParametrik.replace("x" + (j+1), SolusiParametrik.get("x" + (j+1)) + "-" + String.format("%.2f", this.Mat[i][k]) + SolusiParametrik.get("x" + (k+1)));
							}else if (this.Mat[i][k] < 0){
								SolusiParametrik.replace("x" + (j+1), SolusiParametrik.get("x" + (j+1)) + String.format("%.2f", (-1)*this.Mat[i][k]) + SolusiParametrik.get("x" + (k+1)));
							}
						}else{
							if (this.Mat[i][k] > 0 || this.Mat[i][k] < 0){
								SolusiParametrik.replace("x" + (j+1), SolusiParametrik.get("x" + (j+1)) + String.format("%.2f", this.Mat[i][k]));
							} 
						}
					}else{
						if (k != this.kol-1){
							if(this.Mat[i][k] > 0){
								SolusiParametrik.replace("x" + (j+1), SolusiParametrik.get("x" + (j+1)) + "-" + String.format("%.2f", this.Mat[i][k]) + SolusiParametrik.get("x" + (k+1)));
							}else if(this.Mat[i][k] < 0){
								SolusiParametrik.replace("x" + (j+1), SolusiParametrik.get("x" + (j+1)) + "+" + String.format("%.2f", (-1)*this.Mat[i][k]) + SolusiParametrik.get("x" + (k+1)));
							}
						}else{
							if(this.Mat[i][k] > 0){
								SolusiParametrik.replace("x" + (j+1), SolusiParametrik.get("x" + (j+1)) + "+" + String.format("%.2f", this.Mat[i][k]));
							}else if (this.Mat[i][k] < 0){
								SolusiParametrik.replace("x" + (j+1), SolusiParametrik.get("x" + (j+1)) + " " + String.format("%.2f", this.Mat[i][k]));
							}
						}
					}
				}
			}else{
				SolusiParametrik.replace("x" + (j+1), "" + String.format("%.2f", this.Mat[i][this.kol-1]));
			}
		}
		return SolusiParametrik;
	}

	public static HashMap<String, String> gaussEliminasi(Matrix mat) {
		mat.MakeEchelon();

		return gaussJordanEliminasi(mat);	
	}

	public static HashMap<String, String> gaussJordanEliminasi(Matrix mat){

		int JmlSolusi;
		HashMap<String, String> Solusi = new HashMap<>();

		mat.MakeReduceEchelon();
		JmlSolusi = mat.jmlSolusi();

		if (JmlSolusi == 0){
			return Solusi;
		}else if( JmlSolusi == 1){
			for (int i = mat.brs-1; i >= 0; i--){
				double Var = mat.Mat[i][mat.kol-1];
				String VarString = String.format("%.2f", Var);
				Solusi.put("x" + (i+1), VarString); 
			}
			return Solusi;
		}else{
			Solusi = mat.MatrixToParam();
			return Solusi;
		}
	}
	
	public static String DisplaySolusi(HashMap<String, String> Solusi){
		
		String str = new String();
		if (Solusi.isEmpty()){
			str = "SPL tidak memiliki solusi";
		}else{
			for (int i = 0; i < Solusi.size(); i++){
				str = str.concat("x" + (i+1) + "=" + Solusi.get("x"+ (i+1)) + " ");	
			}
		}
		return str;
	}

	

	








}
