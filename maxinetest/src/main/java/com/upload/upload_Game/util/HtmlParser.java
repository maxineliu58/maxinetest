//package com.upload.upload_Game.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLDecoder;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class HtmlParser {
///**
//
//* The page to analyze
//
//*/
//
//String htmlUrl;
//
///**
//
//* The results of the analysis
//
//*/
//
//ArrayList hrefList = new ArrayList();
//
///**
//
//* Web page coding
//
//*/
//
//String charSet;
//
//public HtmlParser(String htmlUrl) {
//// TODO Automatically generated constructor stubs
//
//this.htmlUrl = htmlUrl;
//
//}
//
///**
//
//* Obtain analysis results
//
//*
//
//* @throws IOException
//
//*/
//
//public ArrayList getHrefList() throws IOException {
//parser();
//
//return hrefList;
//
//}
//
///**
//
//* Parsing Web Links
//
//*
//
//* @return
//
//* @throws IOException
//
//*/
//
//private void parser() throws IOException {
//URL url = new URL(htmlUrl);
//
//HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//connection.setDoOutput(true);
//
//String contenttype = connection.getContentType();
//
//charSet = getCharset(contenttype);
//
//InputStreamReader isr = new InputStreamReader(
//
//connection.getInputStream());
//
//BufferedReader br = new BufferedReader(isr);
//
//String str = null, rs = null;
//
//while ((str = br.readLine()) != null) {
//rs = getHref(str);
//
//if (rs != null)
//
//hrefList.add(rs);
//
//}
//
//}
//
///**
//
//* Gets the way the web page is encoded
//
//*
//
//* @param str
//
//*/
//
//private String getCharset(String str) {
//Pattern pattern = Pattern.compile("charset=.*");
//
//Matcher matcher = pattern.matcher(str);
//
//if (matcher.find())
//
//return matcher.group(0).split("charset=")[1];
//
//return null;
//
//}
//
///**
//
//* Reads a link from a line of string
//
//*
//
//* @return
//
//*/
//
//private String getHref(String str) {
//Pattern pattern = Pattern.compile("");
//
//Matcher matcher = pattern.matcher(str);
//
//if (matcher.find())
//
//return matcher.group(0);
//
//return null;
//
//}
//
//public static void main(String[] arg) throws IOException {
//HtmlParser a = new HtmlParser("https://www.baidu.com");
//
//ArrayList hrefList = a.getHrefList();
//
//for (int i = 0; i < hrefList.size();i++) {
//
//System.out.println(hrefList.get(i));
//
//}
//}
////public String reflash(int xxx,String url,int type) throws UnsupportedEncodingException {
////Boolean f=false;
////String[] entryUrl=url.split(",");
////
////for(int j=0;jlength;j++){
////if(entryUrl[j]!=null){
////url= entryUrl[j].toString();
////f= GetUrls(sourceId,url,type);
////}
////}
////return JSON.toJSONString(f);
////}
//public Boolean GetUrls(int xxx,String url,int type) throws UnsupportedEncodingException {
//Boolean flag=false;
//
//url = "http://" + url;//This side needs to splice the complete HTTP URL protocol, otherwise the following URL interface cannot be recognized
//String Urlkey= null;
//try {
////Get the source of the web page through the address
//Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
//Matcher matcher = p.matcher(url);
//matcher.find();
////Get the matching level 1 domain name
//Urlkey = matcher.group();
//} catch (Exception e) {
//return flag;
//}
//
//StringBuffer contentBuffer = new StringBuffer();//Used to store the source code of a web page
//try{
//URL u=new URL(url);
//URLConnection uc=u.openConnection();
//InputStream raw=uc.getInputStream();
//InputStreamReader istreamReader = new InputStreamReader(raw, "utf-8");
//// InputStream buffer=new BufferedInputStream(raw);
//// Reader r=new InputStreamReader(buffer);
//BufferedReader buffStr = new BufferedReader(istreamReader);
//String content=null;
//while((content = buffStr.readLine()) != null){
//contentBuffer.append(content).append("\n");
//}
//raw.close();//Close the stream
//}
//catch (IOException e){
//return flag;
//}
////Use regex to match URLs
//// String regex ="";
//// String regex="(?<=(?i)<(a|img)/s+[^>]*/s*(href|src)=")[^>]+?(?="|')(?#>[^>]+)";
//Pattern pt = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
////String regex ="href="([^"]+)"[^>]*>(.*?)";
//String webUrl=null;
//List urlList=new ArrayList<>();//A collection used to hold web addresses
////Pattern pt=Pattern.compile(regex);
//Matcher mt=pt.matcher(contentBuffer.toString());
//while(mt.find())//Gets the URL of the tag
//{
//// //Get the href specific URL
//// Matcher myurl=Pattern.compile("href=.*?>").matcher(mt.group());
//// while(myurl.find())
//// {
//webUrl = mt.group().replaceAll("href=|>","").replaceAll("\""," ").trim();
//if(webUrl.indexOf("javascript")==-1 || webUrl.startsWith("https")){
//webUrl= webUrl.split(" ")[0];
//urlList.add(webUrl);
//
//}
//
//}
//
////Keep the new URL collection
//if(urlList!=null){
//
////for(int i=0;i;i++){
////if(urlList.get(i)!=null){
////String urls= urlList.get(i).toString();
//////Add a legitimate URL
////if(urls.contains(Urlkey) && urls.indexOf("&")==-1){
////urls= URLDecoder.decode(urls.trim(),"utf-8");
////flag= xxx.insertUrls(xx, urls);
////}
////}
////
//}
//}
//}