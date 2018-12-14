package util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import javax.xml.bind.JAXBElement;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DocxUtil {
//	private Object[] value = {
//			"2017013015020303（测试）",
//			"已结案 2000-01-30 14:21",
//			null,
//			null,
//			null,
//			null,
//			"问题来源测试",
//			null,//地图
//			null,
//			"测试反映人（13888888888）",
//			null,
//			null,
//			"大类",
//			null,
//			null,
//			"小类",
//			null,
//			null,
//			"类型说明",
//			null,
//			null,
//			"问题性质",
//			null,
//			null,
//			null,
//			"测试街道",
//			null,
//			"测试社区",
//			null,
//			"测试网格地址",
//			null,
//			"事件等级",
//			null,
//			null,//33
//			null,
//			null,//35
//			null,
//			null,//37
//			null,
//			"事发地址",
//			null,
//			null,
//			"问题描述",
//	};
//	private String[][] ajlc = new String[][]{
//			/*new String[]{"区中心", "受理员001", "2016-1-3 17:05", "备注: 受理员001执行了立案操作", "意见：尽快处理"},
//		new String[]{"区中心", "受理员001", "2016-1-2 17:04", "备注: 受理员001执行了立案操作", "意见：尽快处理"},
//		new String[]{"区中心", "受理员001", "2016-1-1 17:03", "备注: 受理员001执行了立案操作", "意见：尽快处理"}*/
//	};
//	//用流数组替换
//	private byte[][][] sb = new byte[][][]{
//			/*new String[]{"123456.jpg", null, "123456.jpg"},
//		new String[]{"123456.jpg", "123456.jpg", "123456.jpg"}*/
//	};
//	private byte[][][] cz = new byte[][][]{
//			/*new String[]{null, "123456.jpg", "123456.jpg"},*/
//	};
//	private byte[][][] hc = new byte[][][]{
//			/*new String[]{null, "123456.jpg", "123456.jpg"},
//		new String[]{"123456.jpg", "123456.jpg", null}*/
//	};
//	//涉事人数：   123   重点场所：xxx
//	private String[] zzxx = new String[]{
//			/*"5", "有", "当事人1（15000000000） 当事人2（1515151515）当事人1（15000000000） 当事人2（1515151515）当事人1（15000000000） 当事人2（1515151515）"*/
//	};
//
//	private static final int index_zz = 33;//每隔2个
//	private static final int beginZZ = 13;//综治
//	private static final int beginLC = 21;//流程
//	private static final int beginSB = 26;//上报
//	private static final int beginCZ = 28;//处置
//	private static final int beginHC = 30;//核查
//	private static final String path = "diagrams";//class下的包路径
//	private static final String wordName = "print.docx";//模板名
//	private int zl = 0;
//	private boolean isZZ = false;
//	private WordprocessingMLPackage wordMLPackage;
//	private ObjectFactory factory;
//	private String fileSever;
//
//	public DocxUtil(DocxPojo pojo,List<Eventlog> eventlogs,
//			List<Eventfile> reportList, List<Eventfile> disposeList,
//			List<Eventfile> checkList, String fileSever) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		this.fileSever = fileSever;
//		value[0] = pojo.getTaskcodeAndType();
//		value[1] = pojo.getStateAndCreatetime();
//		value[6] = pojo.getSource();
//		if (pojo.getMapPath() != null) {
//			value[7] = getURLResourceAsByteArray(fileSever + pojo.getMapPath());
//		}
//		value[9] = pojo.getReporterAndPhone();
//		value[12] = pojo.getType1();
//		value[15] = pojo.getType2();
//		value[18] = pojo.getType3();
//		value[21] = pojo.getWttype();
//		value[25] = pojo.getStreetOrg();
//		value[27] = pojo.getCountOrg();
//		value[29] = pojo.getGridOrg();
//		value[31] = pojo.getGrade();
//		value[39] = pojo.getAddress();
//		value[42] = pojo.getEventdesc();
//
//		//事件流程
//		ajlc = new String[eventlogs.size()][5];
//		for (int i = 0; i < eventlogs.size(); i++) {
//			Eventlog el = eventlogs.get(i);
//			ajlc[i][0] = EventConstants.SECTIONLOG.get(el.getSection());
//			ajlc[i][1] = el.getUserName();
//			ajlc[i][2] = sdf.format(new Date(el.getCreatetime()));
//			ajlc[i][3] = "备注: " + el.getContent();
//			ajlc[i][4] = "意见: " + el.getNotice();
//		}
//		sb = getByteArray(reportList, 3);//上报
//		cz = getByteArray(disposeList, 3);//处置
//		hc = getByteArray(checkList, 3);//核查
//		//综治
//		if (pojo.isZZ()) {
//			isZZ = true;
//			zzxx = new String[] {pojo.getInvolvenum(), pojo.getKeyaddress(),
//					pojo.getLitigants().replace(",", ":").replace(";", " ")};
//		}
//	}
//
//	private byte[][][] getByteArray(List<Eventfile> list, int size) {
//		byte[][][] result = new byte[(list.size() - 1) / size + 1][size][];
//		for (int i = 0; i < list.size() / size + 1; i++) {
//			for (int j = size * i; j < list.size() && j < (size + size * i); j++) {
//				byte[] array = getURLResourceAsByteArray(fileSever + list.get(j).getNewpath());
//				if (array != null) {
//					result[i][j % size] = array;
//				}
//			}
//		}
//		return result;
//	}
//
//	public void createDocx(OutputStream os) throws Exception {
//		URL url = DocxUtil.class.getClassLoader().getResource(path);
//		wordMLPackage = WordprocessingMLPackage.load(new File(url.getPath() + File.separator + wordName));
//		factory = Context.getWmlObjectFactory();
//		MainDocumentPart part = wordMLPackage.getMainDocumentPart();
//		List<Object> obj = part.getContent();
//		JAXBElement<Tbl> test = (JAXBElement<Tbl>) obj.get(0);
//		Tbl table = test.getValue();
//		int i = 0;
//		if (isZZ) {
//			value[index_zz] = zzxx[0];
//			value[index_zz + 2] = zzxx[1];
//			value[index_zz + 4] = zzxx[2];
//		}
//		for (Object obj2 : table.getContent()) {
//			if (obj2 instanceof Tr) {
//				Tr tr = (Tr) obj2;
//				for (Object obj3 : tr.getContent()) {
//					Tc tc = ((JAXBElement<Tc>)obj3).getValue();
//					P p = (P) tc.getContent().get(0);
//					if (i < value.length && value[i] != null) {
//						setPContent(p, value[i]);
//					}
//					i++;
//				}
//			}
//		}
//		//案卷流程
//		for (String[] strs : ajlc) {
//			Tr tr2 = cloneTr((Tr) (table.getContent().get(beginLC + zl)), strs, false);
//			table.getContent().add(beginLC + zl, tr2);
//			zl++;
//		}
//		table.getContent().remove(beginLC + zl);
//		zl--;
//		//上报图片
//		for (byte[][] strs : sb) {
//			Tr tr2 = cloneTr((Tr) (table.getContent().get(beginSB + zl)), strs, true);
//			table.getContent().add(beginSB + zl, tr2);
//			zl++;
//		}
//		table.getContent().remove(beginSB + zl);
//		zl--;
//		if (sb[0][0] == null) {
//			table.getContent().remove(beginSB + zl);
//			zl--;
//		}
//		//处置图片
//		for (byte[][] strs : cz) {
//			Tr tr2 = cloneTr((Tr) (table.getContent().get(beginCZ + zl)), strs, true);
//			table.getContent().add(beginCZ + zl, tr2);
//			zl++;
//		}
//		table.getContent().remove(beginCZ + zl);
//		zl--;
//		if (cz[0][0] == null) {
//			table.getContent().remove(beginCZ + zl);
//			zl--;
//		}
//		//核查图片
//		for (byte[][] strs : hc) {
//			Tr tr2 = cloneTr((Tr) (table.getContent().get(beginHC + zl)), strs, true);
//			table.getContent().add(beginHC + zl, tr2);
//			zl++;
//		}
//		table.getContent().remove(beginHC + zl);
//		zl--;
//		if (hc[0][0] == null) {
//			table.getContent().remove(beginHC + zl);
//			zl--;
//		}
//		if (!isZZ) { //删除综治信息
//			table.getContent().remove(beginZZ + 1);
//			table.getContent().remove(beginZZ);
//		}
//		wordMLPackage.save(os);
//	}
//
//	private void setPContent(P p, Object content) throws Exception {
//		List<Object> obj = p.getContent();
//		Tc tc = (Tc) p.getParent();
//		if (obj.size() == 0) return;
//		R r = (R) obj.get(0);
//		JAXBElement je = (JAXBElement) r.getContent().get(0);
//		if (je.getValue() instanceof Text) {
//			Text text = (Text) je.getValue();
//			text.setValue(content.toString());
//		} else {
//			Drawing d = (Drawing) ((JAXBElement) r.getContent().get(0)).getValue();
//			CTPositiveSize2D pos = ((Inline)d.getAnchorOrInline().get(0)).getExtent();
//			P paragraphWithImage = addInlineImageToParagraph((byte[]) content, wordMLPackage, pos.getCx(), pos.getCy());
//			tc.getContent().set(0, paragraphWithImage);
//		}
//	}
//
//	/**
//	 * 复制一行，如果是图片传入图片路径，后期可以改成  输入流数组
//	 * @param tr
//	 * @param strs
//	 * @param isImage
//	 * @return
//	 * @throws Exception
//	 */
//	private Tr cloneTr(Tr tr, Object[] strs, boolean isImage) throws Exception {
//		Tr result = factory.createTr();
//		result.setRsidDel(tr.getRsidDel());
//		result.setRsidR(tr.getRsidR());
//		result.setRsidRPr(tr.getRsidRPr());
//		result.setRsidTr(tr.getRsidTr());
//		result.setTblPrEx(tr.getTblPrEx());
//		result.setTrPr(tr.getTrPr());
//		int i = 0;
//		for (Object obj1 : tr.getContent()) {
//			Tc tc = ((JAXBElement<Tc>)obj1).getValue();
//			Tc tc_copy = factory.createTc();
//			tc_copy.setTcPr(tc.getTcPr());
//			result.getContent().add(tc_copy);
//			for (Object obj2 : tc.getContent()) {
//				P p = (P) obj2;
//				P p_copy = factory.createP();
//				if (p.getContent().isEmpty()) {
//					tc_copy.getContent().add(p_copy);
//					break;
//				}
//				p_copy.setPPr(p.getPPr());
//				p_copy.setRsidDel(p.getRsidDel());
//				p_copy.setRsidP(p.getRsidP());
//				p_copy.setRsidR(p.getRsidR());
//				p_copy.setRsidRDefault(p.getRsidRDefault());
//				p_copy.setRsidRPr(p.getRsidRPr());
//				R r = (R) p.getContent().get(0);
//				R r_copy = factory.createR();
//				r_copy.setRPr(r.getRPr());
//				r_copy.setRsidDel(r.getRsidDel());
//				r_copy.setRsidR(r.getRsidR());
//				r_copy.setRsidRPr(r.getRsidRPr());
//				p_copy.getContent().add(r_copy);
//				if (isImage) {
//					if (strs[i] == null) {
//						tc_copy.getContent().add(p_copy);
//					} else {
//						//TODO
//						Drawing d = (Drawing) ((JAXBElement) r.getContent().get(0)).getValue();
//						CTPositiveSize2D pos = ((Inline)d.getAnchorOrInline().get(0)).getExtent();
//						P paragraphWithImage = addInlineImageToParagraph((byte[]) strs[i], wordMLPackage, pos.getCx(), pos.getCy());
//						if (tc_copy.getContent().isEmpty()) {
//							tc_copy.getContent().add(paragraphWithImage);
//						} else {
//							tc_copy.getContent().set(0, paragraphWithImage);
//						}
//					}
//				} else {
//					tc_copy.getContent().add(p_copy);
//					Text text_copy = factory.createText();
//					text_copy.setValue(strs[i].toString());
//					r_copy.getContent().add(text_copy);
//				}
//				i++;
//			}
//		}
//		return result;
//	}
//
//	//用于测试表格包含多少单元数据
//	@Deprecated
//	private boolean test(P p, int i) throws Exception {
//		List<Object> obj = p.getContent();
//		Tc tc = (Tc) p.getParent();
//		if (obj.size() == 0) return true;
//		R r = (R) obj.get(0);
//		JAXBElement je = (JAXBElement) r.getContent().get(0);
//		if (je.getValue() instanceof Text) {
//			Text text = (Text) je.getValue();
//			System.out.println(i + ": " + text.getValue());
//		} else if (je.getValue() instanceof Drawing){
//			System.out.println(i + ": image");
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 *  向新的段落中添加内联图片并返回这个段落.
//	 * @param inline
//	 * @return
//	 */
//	private P addInlineImageToParagraph(byte[] bytes, WordprocessingMLPackage wordMLPackage, long width, long height) throws Exception {
//		if (bytes == null) return factory.createP();
//		int docPrId = 1;
//		int cNvPrId = 2;
//		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
//		Inline inline = imagePart.createImageInline("Filename hint", "Alternative text", docPrId, cNvPrId, width, height, false);
//		P paragraph = factory.createP();
//		R run = factory.createR();
//		paragraph.getContent().add(run);
//		Drawing drawing = factory.createDrawing();
//		run.getContent().add(drawing);
//		drawing.getAnchorOrInline().add(inline);
//		return paragraph;
//	}
//
//	private byte[] InputStreamToByte(InputStream is) throws IOException {
//		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1024];
//		int ch;
//		while ((ch = is.read(buffer)) != -1) {
//			bytestream.write(buffer,0,ch);
//		}
//		byte data[] = bytestream.toByteArray();
//		bytestream.close();
//		return data;
//	}
//
//	public byte[] getURLResourceAsByteArray(String url) {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpGet httpget = null;
//		try {
//			httpget = new HttpGet(url);
//			HttpResponse resp = httpclient.execute(httpget);
//			if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
//				HttpEntity entity = resp.getEntity();
//				InputStream in = entity.getContent();
//				return InputStreamToByte(in);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (httpclient != null) {
//				try {
//					httpclient.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
}
