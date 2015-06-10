package br.com.Transfer;

public class Comparator {
	
	/**
	 * Método que recebe os argumentos para criacao de um objeto
	 * Transf e setando seus valores de acordo com os parametros.
	 * 
	 * @param args
	 */
	public static void setTransf(String[] args, Transfer trf){
		
		trf.setFileSize(new Integer(args[0]));
		trf.setSliceNumbers(new Integer(args[1]));
		trf.setSmallFilesSize(new Integer(args[2]));
		trf.setUploadSpeed(new Float(args[3]));
		trf.setCoreNumbers(new Integer(args[4]));
		trf.setTimeToCompressOneFile(new Integer(args[5]));
		trf.setTimeToBrokeFiles(new Float(args[6]));
		trf.calculateTimeToCompressAllFiles();
		trf.calculateTimeToTransferDirectly();
		trf.calculateTimeToTransferBrokenFiles();
		trf.calculateTimeToBrokeAndCompressAndTransfer();	
		
	}
	
	public static void	showInfo(Transfer trf){
		System.out.println("************************************************************************");
		System.out.println("INFORMAÇẼS: \n");
		System.out.println("Tamanho do arquivo é de : " + trf.getFileSize() +" mbs.");
		System.out.println("Arquivo será quebrado em : " + trf.getSliceNumbers() + " partes de " +trf.getSmallFilesSize() + " mbs.");
		System.out.println("Tempo para quebrar o arquivo é de : " +trf.getTimeToBrokeFiles()+ " segundos.");
		System.out.println("Velocidade de upload do cliente é de : " +trf.getUploadSpeed() +" mb/s.");
		System.out.println("Número de cores da máquina do cliente é : " + trf.getCoreNumbers() +".");
		System.out.println("Tempo de demora pra comprimir um pedaço do arquivo é :" +trf.getTimeToCompressOneFile() +" segundos.");
		System.out.println("Tempo de demora para comprimir todos os arquivos é : " +trf.getTimeToCompressAllFiles() +" segundos.");
		System.out.println("************************************************************************");
		System.out.println("Tempo para enviar o arquivo sem comprimi-lo : " +trf.getTimeToTransferDirectly() +" segundos.");
		System.out.println("Tempo para enviar o arquivo QUEBRADO E COMPRIMIDO : " +trf.getTimeToTransferBrokenAndCompressed() +" segundos.");
		System.out.println("************************************************************************");
	}
	
	public static void main(String[] args){
		
		Transfer trf = new Transfer();	
		System.out.println("************************************************************************");

		Comparator.setTransf(args, trf);
		Comparator.showInfo(trf);
		
		if(trf.getTimeToTransferBrokenAndCompressed() < trf.getTimeToTransferDirectly()){
	
			System.out.println("-------------------------");
			System.out.println("VALE A PENA COMPACTAR !!");
			System.out.println("-------------------------");
			
		}else{
			
			System.out.println("-------------------------");
			System.out.println("NÃO VALE A PENA COMPACTAR");
			System.out.println("-------------------------");
		}
			
	}

}
