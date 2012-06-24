package com.gui.swing;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.BreakIterator;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MultilineTableCellHandler implements TableCellRenderer {

	private TableColumnModel model;
	private int minimumHeight;
	private int lastRowIndex = -1;

	public MultilineTableCellHandler(TableColumnModel model) {
		this.model = model;
		init();
	}

	private void init() {
		Enumeration<TableColumn> e = model.getColumns();
		while (e.hasMoreElements()) {
			e.nextElement().setCellRenderer(this);
		}
	}

	private class CellArea extends DefaultTableCellRenderer {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String text;
		protected int rowIndex;
		protected int columnIndex;
		protected JTable table;
		private int paragraphStart, paragraphEnd;
		private LineBreakMeasurer lineMeasurer;

		public CellArea(String s, JTable tab, int row, int column,
				boolean isSelected) {
			text = s;
			rowIndex = row;
			columnIndex = column;
			table = tab;
			table.getFont();
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			}
		}

		@Override
		public void paintComponent(Graphics gr) {
			super.paintComponent(gr);
			if (lastRowIndex != rowIndex) {
				lastRowIndex = rowIndex;
				minimumHeight = 0;
			}
			if (text != null && !text.isEmpty()) {
				Graphics2D g = (Graphics2D) gr;
				if (lineMeasurer == null) {
					AttributedCharacterIterator paragraph = new AttributedString(
							text).getIterator();
					paragraphStart = paragraph.getBeginIndex();
					paragraphEnd = paragraph.getEndIndex();
					FontRenderContext frc = g.getFontRenderContext();
					lineMeasurer = new LineBreakMeasurer(paragraph,
							BreakIterator.getWordInstance(), frc);
				}
				float breakWidth = table.getColumnModel()
						.getColumn(columnIndex).getWidth();
				float drawPosY = 0;
				lineMeasurer.setPosition(paragraphStart);
				while (lineMeasurer.getPosition() < paragraphEnd) {
					TextLayout layout = lineMeasurer.nextLayout(breakWidth);
					float drawPosX = layout.isLeftToRight() ? 0 : breakWidth
							- layout.getAdvance();
					drawPosY += layout.getAscent();
					layout.draw(g, drawPosX, drawPosY);
					drawPosY += layout.getDescent() + layout.getLeading();
				}
				if (drawPosY > minimumHeight) {
					minimumHeight = (int) Math.ceil(drawPosY);
				}
				if (columnIndex == table.getColumnCount() - 1) {
					table.setRowHeight(rowIndex, minimumHeight);
				}
			}
		}
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		CellArea area = new CellArea(value.toString(), table, row, column,
				isSelected);
		return area;
	}

}
