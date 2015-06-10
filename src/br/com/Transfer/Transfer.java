package br.com.Transfer;

public class Transfer {
	
	private int fileSize;
	private int smallFilesSize;
	private int sliceNumbers;
	private int timeToCompressOneFile;
	private int timeToCompressAllFiles;
	private int coreNumbers ;
	private float uploadSpeed  ;
	private float timeToBrokeFiles;
	private float timeToTransferDirectly ;
	private float timeToTransferBrokenAndCompressed ;
	
	
	public float getTimeToTransferBrokenAndCompressed() {
		return timeToTransferBrokenAndCompressed;
	}

	public void setTimeToTransferBrokenAndCompressed(
			float timeToTransferBrokenAndCompressed) {
		this.timeToTransferBrokenAndCompressed = timeToTransferBrokenAndCompressed;
	}

	public float getTimeToBrokeFiles() {
		return timeToBrokeFiles;
	}

	public void setTimeToBrokeFiles(float timeToBrokeFiles) {
		this.timeToBrokeFiles = timeToBrokeFiles;
	}

	public int getFileSize() {
		return fileSize;
	}
	
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	public int getSliceNumbers() {
		return sliceNumbers;
	}
	
	public void setSliceNumbers(int slicesNumbers) {
		this.sliceNumbers = slicesNumbers;
	}
	
	public int getTimeToCompressOneFile() {
		return timeToCompressOneFile;
	}
	
	public void setTimeToCompressOneFile(int timeToCompressOneFile) {
		this.timeToCompressOneFile = timeToCompressOneFile;
	}
	
	public int getTimeToCompressAllFiles() {
		return timeToCompressAllFiles;
	}
	
	public void setTimeToCompressAllFiles(int timeToCompressAllFiles) {
		this.timeToCompressAllFiles = timeToCompressAllFiles;
	}
	
	public float getUploadSpeed() {
		return uploadSpeed;
	}
	
	public void setUploadSpeed(float uploadSpeed) {
		this.uploadSpeed = uploadSpeed;
	}
	
	public int getCoreNumbers() {
		return coreNumbers;
	}

	public void setCoreNumbers(int coreNumbers) {
		this.coreNumbers = coreNumbers;
	}
	


	public float getTimeToTransferDirectly() {
		return timeToTransferDirectly;
	}

	public void setTimeToTransferDirectly(float timeToTransferDirectly) {
		this.timeToTransferDirectly = timeToTransferDirectly;
	}
	
	public int getSmallFilesSize() {
		return smallFilesSize;
	}

	public void setSmallFilesSize(int smallFilesSize) {
		this.smallFilesSize = smallFilesSize;
	}
	
	/**
	 * Se tiver mais nucleos do que a quantidade de arquivos que voce quebrou,
	 * entao cada 'core' irah comprimir um pedaco do arquivo, e alguns ficarao
	 * osciosos. Com isso , o tempo total serah de compressao de um arquivo.
	 * Caso contrario , todos os nucleos ficarao em funcionamente e o tempo
	 * sera a divisao dos arquivos pelo processador mais um se o numero de 
	 * pedacos nao for multiplo do numero de nucleos.
	 * 
	 */
	public void calculateTimeToCompressAllFiles(){
		
		if(this.getCoreNumbers() >= this.getSliceNumbers()){
			this.setTimeToCompressAllFiles(this.getTimeToCompressOneFile());
		}else{
			if(this.getSliceNumbers()%this.getCoreNumbers() == 0){
				this.setTimeToCompressAllFiles(((this.getSliceNumbers()/this.getCoreNumbers()))*(this.getTimeToCompressOneFile()));
			}else{
				this.setTimeToCompressAllFiles(((this.getSliceNumbers()/this.getCoreNumbers())+1)*(this.getTimeToCompressOneFile()));		
			}
		}
	}
	
	/**
	 * Metodo para calculo de transferencia do arquivo sem quebrar e comprimir
	 */
	public void calculateTimeToTransferDirectly(){
		
		this.setTimeToTransferDirectly(this.getFileSize() / this.getUploadSpeed());
		
	}
	
	/**
	 * Metodo para calculo de tempo de transferencia dos arquivos quebrados
	 */
	public void calculateTimeToTransferBrokenFiles(){
		
		this.setTimeToTransferBrokenAndCompressed((this.getSliceNumbers() * this.getSmallFilesSize()) / this.getUploadSpeed());
		
	}
	
	/**
	 * Metodo para calculo de tempo total para quebrar, comprimir e 
	 * enviar os arquivos quebrados
	 */
	public void calculateTimeToBrokeAndCompressAndTransfer(){
		
		this.setTimeToTransferBrokenAndCompressed(this.getTimeToBrokeFiles() + this.getTimeToCompressAllFiles() + this.getTimeToTransferBrokenAndCompressed() );
		
	}
}
